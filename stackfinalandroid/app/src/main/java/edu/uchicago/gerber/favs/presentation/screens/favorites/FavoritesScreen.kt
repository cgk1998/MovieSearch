package edu.uchicago.gerber.favs.presentation.screens.favorites

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.favs.R
import edu.uchicago.gerber.favs.presentation.navagation.Screen
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel
import edu.uchicago.gerber.favs.presentation.widgets.BottomNavigationBar
import edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging.BookRow as BookRow1

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoritesScreen(navController: NavController,
                    favViewModel: FavViewModel) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 1.dp,

                ) {
                Text(
                    text = "Favorites",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            favViewModel.getFavByEmail()
            println("*******For loop*********")
            for (movie in favViewModel.favMovies.value) {
                print("****************")
                print(movie)
                edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging.BookRow(book = favViewModel.transform(movie!!)) {
                    //the following lines define the onItemClick behavior
                    favViewModel.setMovie(favViewModel.transform(movie))
                    navController.navigate(
                        route = Screen.FavDetail.route
                    )
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    //FavoritesScreen()
}