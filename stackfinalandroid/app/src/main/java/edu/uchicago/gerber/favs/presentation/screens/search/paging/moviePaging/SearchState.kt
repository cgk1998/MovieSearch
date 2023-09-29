package edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging

import androidx.paging.PagingData
import edu.uchicago.gerber.favs.data.models.Search


import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchOperation: SearchOperation = SearchOperation.INITIAL,
    val data: Flow<PagingData<Search>>? = null
)

enum class SearchOperation { LOADING, INITIAL, DONE }