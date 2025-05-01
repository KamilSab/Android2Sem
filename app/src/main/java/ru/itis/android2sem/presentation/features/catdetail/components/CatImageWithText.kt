package ru.itis.android2sem.presentation.features.catdetail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.itis.android2sem.domain.models.Cat
import ru.itis.android2sem.presentation.features.catlist.components.CatImage

@Composable
fun CatImageWithText(
    cat: Cat,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cat with custom text",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )

        CatImage(
            cat = cat,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(8.dp)
        )

        Text(
            text = "Powered by Cataas API",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}