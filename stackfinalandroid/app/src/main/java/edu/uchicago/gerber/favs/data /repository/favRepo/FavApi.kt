package edu.uchicago.gerber.favs.data.repository.favRepo


import edu.uchicago.gerber.favs.data.models.MovieDB
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Singleton


@Singleton
interface FavApi {

    //this will manage generating the query string and using Retrofit to send GET request to api
    @GET(value = "/movies")
    suspend fun getFavs(
    ): Response<MutableList<MovieDB>>


    @GET(value = "/movies/email/{email}")
    suspend fun getFavsByEmail(@Path("email") email: String
    ): Response<MutableList<MovieDB>>


    @GET(value = "/movies/title/{title}")
    suspend fun getMoviesByTitle(@Path("title") title: String
    ):Response<MutableList<MovieDB>>


    @POST(value = "/movies")
    suspend fun addFav(@Body body: MovieDB
    ): Response<MutableList<MovieDB>>

    @DELETE(value = "/movies/delete/{id}")
    suspend fun delete(@Path("id") id : String
    ): Response<MutableList<MovieDB>>

    @PUT(value = "/movies/update/{id}")
    suspend fun update(@Path("id") id : String, @Body body: MovieDB
    ):Response<MutableList<MovieDB>>

}