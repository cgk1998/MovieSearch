package edu.uchicago.gerber.favs.screens


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.uchicago.gerber.favs.presentation.screens.contact.ContactScreen
import edu.uchicago.gerber.favs.presentation.screens.details.DetailsScreen
import edu.uchicago.gerber.favs.presentation.screens.favorites.FavoritesScreen
import edu.uchicago.gerber.favs.presentation.screens.search.SearchScreen
import edu.uchicago.gerber.favs.presentation.navagation.Screen
import edu.uchicago.gerber.favs.presentation.screens.details.CommentScreen
import edu.uchicago.gerber.favs.presentation.screens.details.FavDetailsScreen
import edu.uchicago.gerber.favs.presentation.screens.login.ConfirmScreen
import edu.uchicago.gerber.favs.presentation.screens.login.LoginScreen
import edu.uchicago.gerber.favs.presentation.screens.login.RegisterScreen
import edu.uchicago.gerber.favs.presentation.viewmodels.FavViewModel



@Composable
fun Navigation(
    navController: NavHostController
) {

    val favViewModel: FavViewModel = hiltViewModel()
    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Search.route) {
            SearchScreen(favViewModel = favViewModel, navController = navController)
        }
        composable(Screen.Favorites.route) {
            FavoritesScreen(navController, favViewModel = favViewModel)
        }

        composable(Screen.Contact.route) {
            ContactScreen(navController, favViewModel = favViewModel)
        }

        composable(Screen.Register.route) {
            RegisterScreen(navController, viewModel = favViewModel)
        }

        composable(Screen.Detail.route) { backStackEntry ->
            DetailsScreen(navController = navController, favViewModel = favViewModel)
        }

        composable(Screen.Confirm.route) { backStackEntry ->
            ConfirmScreen(navController = navController, viewModel = favViewModel)
        }

        composable(Screen.Login.route) {
            LoginScreen(navController = navController, viewModel = favViewModel)
        }

        composable(Screen.FavDetail.route) {
            FavDetailsScreen(navController = navController, favViewModel = favViewModel)
        }

        composable(Screen.Comment.route){
            CommentScreen(navController = navController, favViewModel = favViewModel)
        }

    }
}

