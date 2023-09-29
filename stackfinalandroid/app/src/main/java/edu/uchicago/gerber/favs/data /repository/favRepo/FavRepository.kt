package edu.uchicago.gerber.favs.data.repository.favRepo



import edu.uchicago.gerber.favs.data.models.MovieDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class FavRepository @Inject constructor(private val favApi: FavApi) {

    //this must be called on a background thread b/c it is long-running
    //here, I pass in the parameters I need, which then re-pass to the instantated interface

    suspend fun getFavs(
    ): Response<MutableList<MovieDB>> {
        return withContext(Dispatchers.IO) {
            favApi.getFavs()
        }
    }

    suspend fun getFavsByEmial(email: String
    ): Response<MutableList<MovieDB>> {
        return withContext(Dispatchers.IO) {
            favApi.getFavsByEmail(email)
        }
    }

    suspend fun getMoviesByTitle(title:String
    ):Response<MutableList<MovieDB>> {
        return withContext(Dispatchers.IO) {
            favApi.getMoviesByTitle(title)
        }
    }

    suspend fun addFavs( movieDB: MovieDB
    ): Response<MutableList<MovieDB>> {
        return withContext(Dispatchers.IO) {
            favApi.addFav(movieDB)
        }
    }

    suspend fun update( id: String, movieDB: MovieDB
    ):Response<MutableList<MovieDB>> {
        return withContext(Dispatchers.IO){
            favApi.update(id, movieDB)
        }
    }

    suspend fun delete( id: String
    ): Response<MutableList<MovieDB>> {
        return withContext(Dispatchers.IO) {
            favApi.delete(id)
        }
    }


}
