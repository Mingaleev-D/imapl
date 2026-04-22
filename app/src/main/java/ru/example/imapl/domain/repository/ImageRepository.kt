package ru.example.imapl.domain.repository

import ru.example.imapl.domain.model.UnsplashImage

interface ImageRepository {

    suspend fun getEditorFeedImages(): List<UnsplashImage>
    suspend fun getImage(imageId: String): UnsplashImage
}