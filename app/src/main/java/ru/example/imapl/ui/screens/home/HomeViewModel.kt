package ru.example.imapl.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.example.imapl.data.remote.UnsplashApiService
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: UnsplashApiService,
) : ViewModel() {

    var images: String by mutableStateOf("")

    init {
        getImages()
    }

    private fun getImages() {
        viewModelScope.launch {
            images = apiService.getEditFeedImg()
        }
    }
}
