package edu.uchicago.gerber.favs.data.repository.movieRepo


import edu.uchicago.gerber.favs.data.models.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface MoviesApi {

    //https://developers.google.com/books/docs/v1/using
    //this will manage generating the query string and using Retrofit to send GET request to api
    @GET(value = "/")
    suspend fun getMovies(
        @Query("apikey") apiKey: String,
        @Query("s") query: String,
        @Query("page") page: Int,
    ): Response<MovieResponse>
}