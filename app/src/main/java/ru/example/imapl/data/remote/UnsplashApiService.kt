package ru.example.imapl.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import ru.example.imapl.core.utils.Constants.API_KEY

interface UnsplashApiService {

    @Headers("Authorization: Client-ID $API_KEY")
    @GET("photos")
    suspend fun getEditFeedImg(): String
}