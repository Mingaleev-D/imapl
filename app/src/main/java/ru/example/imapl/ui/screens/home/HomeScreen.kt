package ru.example.imapl.ui.screens.home

import androidx.appcompat.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.example.imapl.data.remote.dto.UnsplashImageDto
import ru.example.imapl.domain.model.UnsplashImage
import ru.example.imapl.ui.screens.home.components.ImageCard
import ru.example.imapl.ui.screens.home.components.ImageVistaAppBar
import ru.example.imapl.ui.screens.home.components.ImagesVerticalGrid

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    images: List<UnsplashImage>,
    onImageClick: (String) -> Unit,
    onSettingsClick: () -> Unit,
    onFabClick: () -> Unit,
) {

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ImageVistaAppBar(
                scrollBehavior = scrollBehavior,
                onSearchClick = onSettingsClick,
            )

            ImagesVerticalGrid(
                images = images,
                onImageClick = onImageClick,
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

    }

}