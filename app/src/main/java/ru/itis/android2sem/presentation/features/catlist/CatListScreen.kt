package ru.itis.android2sem.presentation.features.catlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import ru.itis.android2sem.domain.models.Cat
import ru.itis.android2sem.presentation.common.ShimmerAnimation
import ru.itis.android2sem.presentation.common.UiState
import ru.itis.android2sem.presentation.navigation.Screen
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(
    navController: NavController,
    viewModel: CatListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Random Cats") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.loadRandomCat() }) {
                Icon(Icons.Default.Refresh, "Refresh")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (state) {
                is UiState.Loading -> ShimmerAnimation()
                is UiState.Success<*> -> {
                    val cat = (state as UiState.Success<Cat>).data
                    AsyncImage(
                        model = cat.imageUrl,
                        contentDescription = "Random cat",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                navController.navigate(Screen.CatDetail.createRoute("Hello Cat"))
                            },
                        contentScale = ContentScale.Crop
                    )
                }
                is UiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = (state as UiState.Error).message,
                            color = MaterialTheme.colorScheme.error
                        )
                        Button(onClick = { viewModel.loadRandomCat() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}