package ru.example.imapl.ui.screens.fullImage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.example.imapl.core.navigation.Routes
import ru.example.imapl.domain.model.UnsplashImage
import ru.example.imapl.domain.repository.ImageRepository
import javax.inject.Inject

@HiltViewModel
class FullImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var image: UnsplashImage? by mutableStateOf(null)
        private set

    private val imageId = savedStateHandle.toRoute<Routes.FullImageScreen>().imageId

    init {
        getImage()
    }

    private fun getImage() {
        viewModelScope.launch {
            try {
                image = imageRepository.getImage(imageId = imageId)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}