package com.discoverita.pixum.comic

data class ComicsResult(
    val id: String,
    val title: String,
    val description: String,
    val thumbnail: ComicThumbnail
) {
    data class ComicThumbnail(val path: String, val extension: String)
}