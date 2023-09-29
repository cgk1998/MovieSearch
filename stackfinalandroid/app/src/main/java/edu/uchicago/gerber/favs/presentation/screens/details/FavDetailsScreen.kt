package edu.uchicago.gerber.favs.presentation.screens.details


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.skydoves.landscapist.glide.GlideImage
import edu.uchicago.gerber.favs.R
import edu.uchicago.gerber.favs.data.models.MovieDB
import edu.uchicago.gerber.favs.presentation.navagation.Screen
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavDetailsScreen(
    navController: NavController,
    favViewModel: FavViewModel
) {

    //observe the business
    val movie = favViewModel.movie.value
    val activity = (LocalContext.current as? Activity)


    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 1.dp
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier
                        .clickable {
                            navController.popBackStack()
                        }
                        .align(Alignment.CenterVertically)
                        .padding(20.dp, 0.dp, 0.dp, 0.dp))



                Text(
                    text = "Details",
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                ) {


                }

            }

        }
    }) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState(0))
                .padding(20.dp, 0.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {


                Divider()
                Spacer(Modifier.height(20.dp))
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp),
                    imageModel = movie.poster ?: "https://picsum.photos/id/1026/200/300",
                    // Crop, Fit, Inside, FillHeight, FillWidth, None
                    contentScale = ContentScale.Fit

                )
                with(movie){
                    title?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Start,
                            fontSize = 22.sp
                        )
                    }
                    year?.let {
                        Text(
                            text = it,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.Normal),
                            textAlign = TextAlign.Start,
                            fontSize = 16.sp
                        )
                    }
                }


                Button(
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth(1f),

                    onClick = {
                        favViewModel.deleteFav(movie.id!!)
                        Toast.makeText(activity, "Removed From My Favourite.  Click right up icon to view.", Toast.LENGTH_LONG).show()
                        navController.navigate(
                            route = Screen.Favorites.route
                        )
                    },

                    colors =
                    ButtonDefaults.buttonColors(backgroundColor = Color.Red)

                ) {
                    Text(text = "Remove From My Favorite")
                }

                val context = LocalContext.current

                movie.imdbID?.let {
                    Button(
                        modifier =
                        Modifier
                            .padding(10.dp, 0.dp)
                            .fillMaxWidth(1f),

                        onClick = {
                            val openURL = Intent(android.content.Intent.ACTION_VIEW)
                            openURL.data = Uri.parse("https://www.imdb.com/title/${movie.imdbID}/")
                            context.startActivity(openURL)
                        },

                        colors =
                        ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)

                    ) {
                        Text(text = "View it in IMDB")
                    }
                }


                Button(
                    modifier =
                    Modifier
                        .padding(10.dp, 0.dp)
                        .fillMaxWidth(1f),

                    onClick = {
                        navController.navigate(
                            route = Screen.Comment.route
                        )
                    },

                    colors =
                    ButtonDefaults.buttonColors(backgroundColor = Color.Blue)

                ) {
                    Text(text = "Comment")
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Comments",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Start,
                    fontSize = 22.sp
                )
                favViewModel.getMoviesByTitle(movie.title!!)

                for (movie in favViewModel.movieComments.value) {
                    if (movie.comment != "") {
                        MessageRow(email = movie.email!!, comment = movie.comment!!) {
                            //the following lines define the onItemClick behavior
                        }
                    }

                }

            }
        }
    }

}

@Preview(showBackground = true)
@ExperimentalAnimationApi
@Composable
fun favDetailsScreenPreview() {

//    DetailsScreen(
//        navController = rememberNavController(),
//        context = null
//    )
}


