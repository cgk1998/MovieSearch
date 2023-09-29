package edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging


import android.app.Application
import androidx.paging.PagingSource
import androidx.paging.PagingState
import edu.uchicago.gerber.favs.data.models.Search
import edu.uchicago.gerber.favs.data.repository.movieRepo.MoviesRepository


import javax.inject.Inject

class MovieSource @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val paginateData: Paginate,
    private val application: Application
) :
    PagingSource<Int, Search>() {
    override fun getRefreshKey(state: PagingState<Int, Search>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Search> {
        return try {
            val prev = params.key ?: 1

            val response = moviesRepository.getBooks(
                page = prev,
                apiKey = "3fea668c",
                query = paginateData.query,
            )

            if (response.isSuccessful) {
                val body = response.body()?.search

                LoadResult.Page(
                    data = body!!,
                    prevKey = if (prev == 1) null else prev - 1,
                    nextKey = if (body.size < 10) null else prev + 1
                )
            } else {
                LoadResult.Error(Exception(response.message()))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true

}