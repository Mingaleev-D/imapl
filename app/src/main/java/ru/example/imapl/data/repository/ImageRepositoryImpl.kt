package ru.example.imapl.data.repository

import ru.example.imapl.data.remote.UnsplashApiService
import ru.example.imapl.domain.model.UnsplashImage
import ru.example.imapl.domain.model.toUnsplashDomain
import ru.example.imapl.domain.repository.ImageRepository

class ImageRepositoryImpl(
    private val apiService: UnsplashApiService
) : ImageRepository {

    override suspend fun getEditorFeedImages(): List<UnsplashImage> {
        return apiService.getEditFeedImg().map { it.toUnsplashDomain() }
    }
}