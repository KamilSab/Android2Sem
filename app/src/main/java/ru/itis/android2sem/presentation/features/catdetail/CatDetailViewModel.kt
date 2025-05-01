package ru.itis.android2sem.presentation.features.catdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itis.android2sem.domain.models.Cat
import ru.itis.android2sem.domain.usecases.GetCatWithTextUseCase
import ru.itis.android2sem.presentation.common.UiState
import javax.inject.Inject

@HiltViewModel
class CatDetailViewModel @Inject constructor(
    private val getCatWithTextUseCase: GetCatWithTextUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<Cat>>(UiState.Loading)
    val state: StateFlow<UiState<Cat>> = _state.asStateFlow()

    fun loadCatWithText(text: String) {
        viewModelScope.launch {
            _state.value = UiState.Loading
            _state.value = try {
                UiState.Success(getCatWithTextUseCase(text))
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Failed to generate cat with text")
            }
        }
    }
}