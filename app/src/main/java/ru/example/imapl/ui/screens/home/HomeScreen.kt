package ru.example.imapl.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.example.imapl.data.remote.dto.UnsplashImageDto
import ru.example.imapl.domain.model.UnsplashImage

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    images: List<UnsplashImage>,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {

        images.forEach {
            Text(text = it.id)
            Text(text = it.imageUrlRegular)
            Text(text = it.photographerName)
            Text(text = it.height.toString())

            HorizontalDivider()
        }

    }

}