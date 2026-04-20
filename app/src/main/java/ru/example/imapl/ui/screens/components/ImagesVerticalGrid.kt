package ru.example.imapl.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import ru.example.imapl.domain.model.UnsplashImage

@Composable
fun ImagesVerticalGrid(
    modifier: Modifier = Modifier,
    images: List<UnsplashImage>,
    onImageClick: (String) -> Unit,
    onImageDragStart: (UnsplashImage) -> Unit,
    onImageDragEnd: () -> Unit,
) {

    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Adaptive(minSize = 120.dp),
        contentPadding = PaddingValues(10.dp),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(images) { image ->
            ImageCard(
                imageUrl = image,
                modifier = Modifier
                    .clickable { image?.id?.let { onImageClick(it) } }
                    .pointerInput(
                        Unit
                    ) {
                        detectDragGesturesAfterLongPress(
                            onDragStart = { onImageDragStart(image) },
                            onDragEnd = { onImageDragEnd() },
                            onDragCancel = {onImageDragEnd()},
                            onDrag = { _, _ -> },
                        )
                    })
        }
    }

}