package ru.example.imapl.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.example.imapl.domain.model.UnsplashImage
import ru.example.imapl.ui.screens.components.ImageVistaAppBar
import ru.example.imapl.ui.screens.components.ImagesVerticalGrid
import ru.example.imapl.ui.screens.components.ZoomImageCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    images: List<UnsplashImage>,
    onImageClick: (String) -> Unit,
    onSearchClick: () -> Unit,
    onFabClick: () -> Unit,
) {

    var showImagePreview by remember { mutableStateOf(false) }
    var activeImage by remember { mutableStateOf<UnsplashImage?>(null) }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImageVistaAppBar(
                scrollBehavior = scrollBehavior,
                onSearchClick = onSearchClick,
            )

            ImagesVerticalGrid(
                images = images,
                onImageClick = onImageClick,
                onImageDragStart = { image ->
                    activeImage = image
                    showImagePreview = true
                },
                onImageDragEnd = {
                    showImagePreview = false
                }
            )
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            onClick = onFabClick,
        ) {
            Icon(
                painter = painterResource(ru.example.imapl.R.drawable.save_24),
                contentDescription = "Save",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        ZoomImageCard(
            modifier = Modifier.padding(20.dp),
            isVisibility = showImagePreview,
            imageUrl = activeImage,
        )

    }

}