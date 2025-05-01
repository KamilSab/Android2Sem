package ru.itis.android2sem.presentation.features.catlist

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.android2sem.domain.models.Cat
import ru.itis.android2sem.domain.usecases.GetRandomCatUseCase
import ru.itis.android2sem.presentation.common.UiState
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CatListViewModel @Inject constructor(
    private val getRandomCatUseCase: GetRandomCatUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<Cat>>(UiState.Loading)
    val state: StateFlow<UiState<Cat>> = _state.asStateFlow()

    init {
        loadRandomCat()
    }

    fun loadRandomCat() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            _state.value = try {
                UiState.Success(getRandomCatUseCase())
            } catch (e: Exception) {
                UiState.Error(e.message ?: "Couldn't load cat")
            }
        }
    }
}