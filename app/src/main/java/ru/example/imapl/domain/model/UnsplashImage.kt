package ru.example.imapl.domain.model

import ru.example.imapl.data.remote.dto.UnsplashImageDto

data class UnsplashImage(
    val id: String,
    val imageUrlSmall: String,
    val imageUrlRegular: String,
    val imageUrlRaw: String,
    val photographerName: String,
    val photographerUsername: String,
    val photographerProfileImgUrl: String,
    val photographerProfileLink: String,
    val width: Int,
    val height: Int,
    val description: String?
)

fun UnsplashImageDto.toUnsplashDomain(): UnsplashImage {
    return UnsplashImage(
        id = id,
        imageUrlSmall = urls.small,
        imageUrlRegular = urls.regular,
        imageUrlRaw = urls.raw,
        photographerName = user.name,
        photographerUsername = user.username,
        photographerProfileImgUrl = user.profileImage?.small.orEmpty(),
        photographerProfileLink = user.links?.html.orEmpty(),
        width = width,
        height = height,
        description = description
    )
}

fun List<UnsplashImageDto>.toUnsplashDomainList(): List<UnsplashImage> {
    return map { it.toUnsplashDomain() }
}
