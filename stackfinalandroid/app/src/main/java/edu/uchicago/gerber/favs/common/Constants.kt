package edu.uchicago.gerber.favs.common

import com.google.gson.Gson
import edu.uchicago.gerber.favs.data.models.MovieResponse
import edu.uchicago.gerber.favs.data.models.Search

object Constants {

    val movieUrl = "https://www.omdbapi.com/"
//    val lightSailUrl = "https://quarkus-and-mongo.dgtp4r65rfgjq.us-east-2.cs.amazonlightsail.com/"
    val lightSailUrl = "https://quarks-and-mongo-v2.dgtp4r65rfgjq.us-east-2.cs.amazonlightsail.com/"
    //this is a fakeBusiness used for debugging and preview

    val fakeMovie: Search
    //use init to parse the raw response-body to business
    init {

        val gson2 = Gson()


        val fakeJson = """
            {"Search":[{"Title":"Love, Rosie","Year":"2014","imdbID":"tt1638002","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTk0Mzg1MTU1MF5BMl5BanBnXkFtZTgwMjU3ODI2MzE@._V1_SX300.jpg"},{"Title":"From Russia with Love","Year":"1963","imdbID":"tt0057076","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BYzQ3ODdjNjQtNzVkYi00YmM5LThkYjMtZDFmYmYxZjZlNmQ4XkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_SX300.jpg"},{"Title":"Love and Monsters","Year":"2020","imdbID":"tt2222042","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BYWVkMWEyMDUtZTVmOC00MTYxLWE1ZTUtNjk4M2IzMjY2OTIxXkEyXkFqcGdeQXVyMDk5Mzc5MQ@@._V1_SX300.jpg"},{"Title":"From Paris with Love","Year":"2010","imdbID":"tt1179034","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BODAwMDFjNjktMWY2Mi00MmVhLWI0MjYtNzg4OTI0NzA5YzBjXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg"},{"Title":"Love, Simon","Year":"2018","imdbID":"tt5164432","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BNTMyZDdiMzUtZjcxNS00Mjc3LTljY2UtYjI4YmY5NzJlYjc1XkEyXkFqcGdeQXVyMTA5OTkwNTc@._V1_SX300.jpg"},{"Title":"Eat Pray Love","Year":"2010","imdbID":"tt0879870","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTY5NDkyNzkyM15BMl5BanBnXkFtZTcwNDQyNDk0Mw@@._V1_SX300.jpg"},{"Title":"I Love You Phillip Morris","Year":"2009","imdbID":"tt1045772","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BNTUwZGU1ZTAtOGI4NC00ZDBlLTgxOGItNzdhOWFlNDczZDcxXkEyXkFqcGdeQXVyMjgyNjk3MzE@._V1_SX300.jpg"},{"Title":"To Rome with Love","Year":"2012","imdbID":"tt1859650","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTcwNTg4MDMxM15BMl5BanBnXkFtZTcwNjAzMzY3Nw@@._V1_SX300.jpg"},{"Title":"Stuck in Love.","Year":"2012","imdbID":"tt2205697","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BMTU1NzI5MDU3OV5BMl5BanBnXkFtZTcwNTE0NDMzOQ@@._V1_SX300.jpg"},{"Title":"Love Me If You Dare","Year":"2003","imdbID":"tt0364517","Type":"movie","Poster":"https://m.media-amazon.com/images/M/MV5BNjIwOGJhY2QtMTA5Yi00MDhlLWE5OTgtYmIzZDNlM2UwZjMyXkEyXkFqcGdeQXVyNTA4NzY1MzY@._V1_SX300.jpg"}],"totalResults":"20588","Response":"True"}
        """

        val fakeMovieResponse =
            gson2.fromJson<MovieResponse>(fakeJson, MovieResponse::class.java)

        fakeMovie = fakeMovieResponse.search!![0]


    }

}