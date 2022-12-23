package com.discoverita.pixum.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.discoverita.pixum.api.MarvelApi
import com.discoverita.pixum.ui.comics.ComicsPagingSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor(
    private val marvelApi: MarvelApi
) {
    fun fetchAllComics() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50, enablePlaceholders = false),
        pagingSourceFactory = { ComicsPagingSource(marvelApi) }
    ).flow

}