package ru.itis.android2sem.presentation.features.catdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.itis.android2sem.presentation.common.ShimmerAnimation
import ru.itis.android2sem.presentation.common.UiState
import ru.itis.android2sem.presentation.features.catdetail.components.CatImageWithText
import ru.itis.android2sem.presentation.features.catlist.components.ErrorScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatDetailScreen(
    text: String,
    navController: NavController? = null,
    viewModel: CatDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(text) {
        viewModel.loadCatWithText(text)
    }

    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Cat with Text") },
                navigationIcon = {
                    if (navController != null) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (state) {
                is UiState.Loading -> ShimmerAnimation()
                is UiState.Success -> {
                    CatImageWithText(
                        cat = state.data,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is UiState.Error -> {
                    ErrorScreen(
                        message = state.message,
                        onRetry = { viewModel.loadCatWithText(text) }
                    )
                }
            }
        }
    }
}