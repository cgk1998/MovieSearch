package edu.uchicago.gerber.favs.presentation.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.uchicago.gerber.favs.presentation.navagation.Screen
import edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging.BookList
import edu.uchicago.gerber.favs.presentation.screens.search.paging.moviePaging.SearchOperation

import edu.uchicago.gerber.favs.presentation.widgets.BottomNavigationBar
import edu.uchicago.gerber.favs.screens.CustomTextField
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel



@Composable
fun SearchScreen(
    favViewModel: FavViewModel,
    navController: NavController,

    ) {
    val state = favViewModel.searchState.value
    val queryText = favViewModel.queryText.value

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent,
            elevation = 1.dp,

            ) {

            Icon(imageVector = Icons.Default.ExitToApp,
                contentDescription = "Exit",
                modifier = Modifier
                    .clickable {
                        favViewModel.setEmailText("")
                        favViewModel.setPassText("")
                        favViewModel.setQueryText("")
                        navController.navigate(
                            route = Screen.Login.route
                        )
                    }
                    .align(Alignment.CenterVertically)
                    .padding(20.dp, 0.dp, 0.dp, 0.dp))

            Text(
                text = "Search Movies",
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
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomTextField(
                title = "Search term(s)",
                placeHolder = "e.g. godfather",
                textState = queryText,
                onTextChange = favViewModel::setQueryText,
                keyboardType = KeyboardType.Text,
                ImeAction.Search,
                favViewModel::onSearch
            )


            Spacer(modifier = Modifier.height(10.dp))
            when (state.searchOperation) {
                SearchOperation.LOADING -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(12.dp)
                                .align(
                                    Alignment.Center
                                )
                        )
                    }
                }
                SearchOperation.DONE -> {
                    BookList(favViewModel, navController)
                }
                else -> {
                    Box {}
                }
            }

        }
    }
}

