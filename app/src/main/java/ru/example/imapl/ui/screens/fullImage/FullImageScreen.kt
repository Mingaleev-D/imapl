package ru.example.imapl.ui.screens.fullImage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.animateZoomBy
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch
import ru.example.imapl.R
import ru.example.imapl.core.utils.rememberWindowsInsetsController
import ru.example.imapl.core.utils.toggleStatusBars
import ru.example.imapl.domain.model.UnsplashImage
import ru.example.imapl.ui.screens.components.ImageVistaLoadingBar
import kotlin.math.max

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun FullImageScreen(
    modifier: Modifier = Modifier,
    image: UnsplashImage?,
    onBackClick: () -> Unit,
    onPhotoImgClick: (String) -> Unit,
    // onDownloadClick: () -> Unit,
) {

//    var showBars by rememberSaveable { mutableStateOf(false) }
//    val windowInsertsController = rememberWindowsInsetsController()
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = image?.imageUrlRaw,
        onState = { imageState ->
            isLoading = imageState is AsyncImagePainter.State.Loading
            isError = imageState is AsyncImagePainter.State.Error
        }
    )

//    LaunchedEffect(key1 = Unit) {
//        windowInsertsController.toggleStatusBars(show = showBars)
//    }


    Box(
        modifier = modifier.fillMaxSize(),
       // contentAlignment = Alignment.Center
    ) {

        BoxWithConstraints(
            modifier = Modifier.fillMaxSize(),
        ) {

            var scale by remember { mutableFloatStateOf(1f) }
            var offset by remember { mutableStateOf(Offset.Zero) }
            val isImageZoomed by remember { derivedStateOf { scale != 1f } }
            val transformState = rememberTransformableState { zoomChange, offsetChange, _ ->
                scale = max(scale * zoomChange, 1f)
                val maxX = (constraints.maxWidth * (scale - 1)) / 2
                val maxY = (constraints.maxHeight * (scale - 1)) / 2
                offset = Offset(
                    x = (offset.x + offsetChange.x).coerceIn(-maxX, maxX),
                    y = (offset.y + offsetChange.y).coerceIn(-maxY, maxY),
                )
            }

            if (isLoading) {
                ImageVistaLoadingBar(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .transformable(state = transformState)
                    .combinedClickable(
                        onDoubleClick = {
                            if (isImageZoomed) {
                                scale = 1f
                                offset = Offset.Zero
                            } else {
                                scope.launch {
                                    transformState.animateZoomBy(
                                        zoomFactor = 3f
                                    )
                                }

                            }
                        },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
//                            showBars = !showBars
//                            windowInsertsController.toggleStatusBars(show = showBars)
                        },
                    )
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offset.x
                        translationY = offset.y
                    },
                painter = if (isError.not()) imageLoader else {
                    painterResource(id = R.drawable.ic_error_24)
                },
                contentDescription = null,
            )
        }

        FullImageViewTopBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth(),
            //.padding(horizontal = 5.dp, vertical = 40.dp),
            image = image,
            onBackClick = onBackClick,
            onPhotoImgClick = onPhotoImgClick,
            onDownloadClick = {},
        )

    }
}

@Composable
fun FullImageViewTopBar(
    modifier: Modifier = Modifier,
    image: UnsplashImage?,
    onBackClick: () -> Unit,
    onPhotoImgClick: (String) -> Unit,
    onDownloadClick: () -> Unit,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier,
            onClick = onBackClick,
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIos,
                contentDescription = null,
            )
        }
        AsyncImage(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape),
            model = image?.photographerProfileImgUrl,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier.clickable {
                image?.let { image ->
                    onPhotoImgClick(image.photographerProfileImgUrl)
                }
            },
        ) {
            Text(
                text = image?.photographerName ?: "Non name",
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = image?.photographerUsername ?: "Non name",
                style = MaterialTheme.typography.bodySmall,
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { onDownloadClick() }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_download_for_offline_24),
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = null,
            )
        }

    }

}