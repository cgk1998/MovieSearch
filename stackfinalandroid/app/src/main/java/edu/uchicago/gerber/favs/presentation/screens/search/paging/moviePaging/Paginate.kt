package edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging

data class Paginate(
    val query: String = "",
    val maxResult: Int = 10,
    val startIndex: Int = 1,
    val page: String = "",
    val apiKey: String = "3fea668c",
)
