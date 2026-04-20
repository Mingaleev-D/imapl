package ru.example.imapl.ui.screens.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import ru.example.imapl.domain.model.UnsplashImage
import ru.example.imapl.ui.theme.ImApLTheme

@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    imageUrl: UnsplashImage,
) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(data = imageUrl.imageUrlSmall)
        .crossfade(true)
        .build()

    val aspectRatio by remember {
        derivedStateOf { (imageUrl.width?.toFloat() ?: 1f) / (imageUrl.height?.toFloat() ?: 1f) }
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .then(Modifier),
        shape = RoundedCornerShape(10.dp)
    ) {
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(
    name = "Ночь-Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "День-Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun ImageCardPreview() {
    // Создаем тестовый объект доменной модели
    val sampleImage = UnsplashImage(
        id = "1",
        imageUrlSmall = "",
        imageUrlRegular = "",
        imageUrlRaw = "",
        photographerName = "John Doe",
        photographerUsername = "johndoe",
        photographerProfileImgUrl = "",
        photographerProfileLink = "",
        width = 1080,
        height = 1080,
        description = "Пример изображения"
    )

    ImApLTheme {
        ImageCard(
            imageUrl = sampleImage,
        )
    }
}

