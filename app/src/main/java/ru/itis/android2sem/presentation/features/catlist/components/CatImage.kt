package ru.itis.android2sem.presentation.features.catlist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ru.itis.android2sem.domain.models.Cat

@Composable
fun CatImage(
    cat: Cat,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(cat.imageUrl)
            .crossfade(true)
            .build()
    )

    Image(
        painter = painter,
        contentDescription = "Random cat image",
        modifier = modifier
            .fillMaxSize()
            .clickable(onClick = onClick),
        contentScale = ContentScale.Crop
    )
}