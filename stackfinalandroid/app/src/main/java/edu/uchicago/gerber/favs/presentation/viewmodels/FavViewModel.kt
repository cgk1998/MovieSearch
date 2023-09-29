package edu.uchicago.gerber.favs.presentation.viewmodels

import android.app.Application
import android.os.StrictMode
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.uchicago.gerber.favs.common.Constants
import edu.uchicago.gerber.favs.data.models.MovieDB
import edu.uchicago.gerber.favs.data.models.Search
import edu.uchicago.gerber.favs.data.models.User
import edu.uchicago.gerber.favs.data.repository.CognitoRepo
import edu.uchicago.gerber.favs.data.repository.favRepo.FavRepository
import edu.uchicago.gerber.favs.data.repository.movieRepo.MoviesRepository
import edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging.MovieSource
import edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging.Paginate
import edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging.SearchOperation
import edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging.SearchState
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject


@HiltViewModel
class FavViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val favRepository: FavRepository,
    private val application: Application,
    private val cogRepo: CognitoRepo
) :
    ViewModel() {

    //////////////////////////////////////////
    // MUTABLE-STATES AND OBSERVABLE STATES
    //////////////////////////////////////////
    private var _queryText = mutableStateOf<String>("")
    val queryText: State<String> = _queryText

//    private var _locationText = mutableStateOf<String>("")
//    val locationText: State<String> = _locationText

    private var _movie = mutableStateOf<Search>(Constants.fakeMovie)
    val movie: State<Search> = _movie

    private var _emailText = mutableStateOf<String>("")
    val emailText: State<String> = _emailText

    private var _passwordText = mutableStateOf<String>("")
    val passwordText: State<String> = _passwordText

//    private var _business = mutableStateOf<Business>(Constants.fakeBusiness)
//    val business: State<Business> = _business

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState

    private val _favMovies = mutableStateOf<MutableList<MovieDB>>(ArrayList())
    val favMovies : State<MutableList<MovieDB>> = _favMovies


    private val _movieComments = mutableStateOf<MutableList<MovieDB>>(ArrayList())
    val movieComments : State<MutableList<MovieDB>> = _movieComments
    //////////////////////////////////////////
    // FUNCTIONS
    //////////////////////////////////////////
//    fun setBusiness( business: Business){
//        _business.value = business
//    }

    fun setMovie(movie: Search) {
        _movie.value = movie
    }

    fun setEmailText(email: String) {
        _emailText.value = email
    }

    fun setPassText(pass: String) {
        _passwordText.value = pass
    }

    fun setQueryText(query: String) {
        _queryText.value = query
    }

//    fun setLocationText(location: String) {
//        _locationText.value = location
//    }


    fun onSearch() {
        _searchState.value = SearchState(searchOperation = SearchOperation.LOADING)
        viewModelScope.launch {
            _searchState.value = SearchState(
                data = Pager(
                    config = PagingConfig(pageSize = 10, prefetchDistance = 5),
                    pagingSourceFactory = {
                        MovieSource(
                            moviesRepository = moviesRepository,
                            paginateData = Paginate(
                                query = _queryText.value
                            ),
                            application
                        )
                    }
                ).flow.cachedIn(viewModelScope),
                searchOperation = SearchOperation.DONE
            )
        }
    }



    fun sendEmail(email: String, subject: String, body: String){
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val client = OkHttpClient();
        val mediaType = "application/json; charset=utf-8".toMediaType();
        val body = "{\"emailFrom\": \"${email}\",\n    \"subject\" : \"${subject}\",\n    \"body\": \"${body}\"}"
        val requestBody = body.toRequestBody(mediaType)
        val request = Request.Builder()
            .url("https://ah3lcbp6uc.execute-api.us-east-2.amazonaws.com/Prod/mail")
            .method("POST", requestBody)
            .addHeader(
                "X-Amz-Content-Sha256",
                "beaead3198f7da1e70d03ab969765e0821b24fc913697e929e726aeaebf0eba3"
            )
            .addHeader("X-Amz-Date", "20220815T020602Z")
            .addHeader(
                "Authorization",
                "AWS4-HMAC-SHA256 Credential=AKIAUW2YU64SZKOHPQEB/20220815/us-east-2/execute-api/aws4_request, SignedHeaders=host;x-amz-content-sha256;x-amz-date, Signature=b7dc20e33911235713d08effa65e62836ef28199080f4e5c84778271ed115fb0"
            )
            .addHeader("Content-Type", "application/json")
            .build()
        val response = client.newCall(request).execute()
        return
    }
    fun transform(movieDB: MovieDB): Search{
        val search = Search()
        search.id = movieDB.id
        search.title = movieDB.title
        search.year = movieDB.year
        search.imdbID = movieDB.imdbID
        search.type = "Movie"
        search.poster = movieDB.poster
        return search
    }

    fun transformToDB(movie: Search): MovieDB{
        val movieDB = MovieDB()
        movieDB.title = movie.title
        movieDB.year = movie.year
        movieDB.imdbID = movie.imdbID
        movieDB.poster = movie.poster
        movieDB.email = _emailText.value
        return movieDB
    }

    fun getMoviesByTitle(title: String){
        viewModelScope.launch {
            try{
                val response = favRepository.getMoviesByTitle(title)
                if (response.isSuccessful){
                    _movieComments.value = response.body()!!
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun getFavByEmail(){
        viewModelScope.launch {
            try {

                val response = favRepository.getFavsByEmial(_emailText.value)
                if (response.isSuccessful) {
                    println("-----------Body--------")

                   _favMovies.value = response.body()!!
                    print(_favMovies.value)

                } else {
                    println("------------failed----------------")
                    print(response.message())
                }

            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun comment(id: String, movie: MovieDB){
        viewModelScope.launch{
            favRepository.update(id, movie)
        }
    }

    fun deleteFav(id:String){
        viewModelScope.launch {
            favRepository.delete(id)
        }
    }

    fun addFav(movie: MovieDB){
        viewModelScope.launch {
            favRepository.addFavs(movie)
        }
    }

    fun registerUser(user: User) = cogRepo.registerUser(user)

    fun verifyOtp(user: User,otp:String) = cogRepo.verifyOtp(otp,user)

    val resendOtp = cogRepo.resendOtp()

    fun login(user: User)  = cogRepo.login(user)

    fun forgetPassword(user: User) = cogRepo.forgetPassword(user)

    fun changeForgetPassword(user: User,otp: String) = cogRepo.changeForgetPassword(otp,user)

    fun resetPassword(user: User,oldPassword:String,newPassword:String) = cogRepo.resetPassword(user,oldPassword,newPassword)


}


