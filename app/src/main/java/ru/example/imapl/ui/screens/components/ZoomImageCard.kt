package ru.example.imapl.ui.screens.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.memory.MemoryCache
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.skydoves.cloudy.Cloudy
import ru.example.imapl.domain.model.UnsplashImage

@Composable
fun ZoomImageCard(
    modifier: Modifier = Modifier,
    isVisibility: Boolean,
    imageUrl: UnsplashImage?,
) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(data = imageUrl?.imageUrlRegular)
        .crossfade(true)
        .placeholderMemoryCacheKey(MemoryCache.Key(imageUrl?.imageUrlSmall ?: ""))
        .build()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        if (isVisibility) {
            Cloudy(
                modifier = Modifier.fillMaxSize(),
                radius = 25,
            ) { }
        }
        AnimatedVisibility(
            modifier = Modifier,
            visible = isVisibility,
            enter = scaleIn() + fadeIn(),
            exit = scaleOut() + fadeOut(),
        ) {
            Card(
                modifier = Modifier,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(10.dp)
                            .clip(CircleShape)
                            .size(25.dp),
                        model = imageUrl?.photographerProfileImgUrl,
                        contentDescription = null,
                    )
                    Text(
                        text = imageUrl?.photographerName ?: "Null",
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = imageRequest,
                    contentDescription = null,

                    )
            }
        }

    }
}