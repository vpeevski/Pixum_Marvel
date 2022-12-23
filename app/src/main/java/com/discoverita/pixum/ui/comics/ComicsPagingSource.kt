package com.discoverita.pixum.ui.comics

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.discoverita.pixum.api.MarvelApi
import com.discoverita.pixum.comic.ComicsResult
import retrofit2.HttpException

private const val STARTING_OFFSET = 0
private const val LOAD_SIZE = 20

class ComicsPagingSource(
    private val marvelApi: MarvelApi
) : PagingSource<Int, ComicsResult>() {

    override fun getRefreshKey(state: PagingState<Int, ComicsResult>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page
                ?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ComicsResult> {
        val position = (params.key ?: STARTING_OFFSET)
        return try {

            val response = marvelApi.getAllComics(
                offset = position,
                limit = params.loadSize
            )
            val comicData = response.data
            val comics = comicData.results

            LoadResult.Page(
                data = comics,
                prevKey = if (position == STARTING_OFFSET) null else position - params.loadSize,
                nextKey = if (comics.isEmpty()) null else position + params.loadSize
            )
        } catch (exception: Exception) {
            Log.i("exception", exception.toString())
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}