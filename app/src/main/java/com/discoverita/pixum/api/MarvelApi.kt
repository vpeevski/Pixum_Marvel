package com.discoverita.pixum.api

import com.discoverita.pixum.comic.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("comics")
    suspend fun getAllComics(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): ComicsResponse
}