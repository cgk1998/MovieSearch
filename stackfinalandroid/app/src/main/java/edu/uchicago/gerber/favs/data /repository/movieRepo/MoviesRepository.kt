package edu.uchicago.gerber.favs.data.repository.movieRepo

import edu.uchicago.gerber.favs.data.models.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

//simple books repo
class MoviesRepository @Inject constructor(private val moviesApi: MoviesApi) {

    //this must be called on a background thread b/c it is long-running
    //here, I pass in the parameters I need, which then re-pass to the instantated interface

    suspend fun getBooks(
        query: String,
        apiKey: String,
        page: Int,
    ): Response<MovieResponse> {
        println("!!!!!!!!!!!!!!!!!!!!!!!!")
        println(query)
        println(apiKey)
        println(page)
        return withContext(Dispatchers.IO) {
            moviesApi.getMovies(
                query = query,
                apiKey = apiKey,
                page = page,
            )
        }
    }
}
