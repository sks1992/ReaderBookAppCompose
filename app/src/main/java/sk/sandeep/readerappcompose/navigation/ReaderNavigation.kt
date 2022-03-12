package sk.sandeep.readerappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sk.sandeep.readerappcompose.ui.screens.details.BookDetailScreen
import sk.sandeep.readerappcompose.ui.screens.home.Home
import sk.sandeep.readerappcompose.ui.screens.login.ReaderLoginScreen
import sk.sandeep.readerappcompose.ui.screens.search.ReaderBookSearchViewModel
import sk.sandeep.readerappcompose.ui.screens.search.SearchScreen
import sk.sandeep.readerappcompose.ui.screens.splash_screen.ReaderSplashScreen
import sk.sandeep.readerappcompose.ui.screens.stats.ReaderStatsScreen
import sk.sandeep.readerappcompose.ui.screens.update.ReaderBookUpdateScreen

@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ReaderScreens.SplashScreen.name
    ) {

        val detailName = ReaderScreens.DetailScreen.name
        composable("$detailName/{bookId}", arguments = listOf(navArgument("bookId") {
            type = NavType.StringType
        })) { backStackEntry ->
            backStackEntry.arguments?.getString("bookId").let {
                BookDetailScreen(navController = navController, bookId = it.toString())
            }
        }

        composable(ReaderScreens.ReaderHomeScreen.name) {
            Home(navController = navController)
        }

        composable(ReaderScreens.LoginScreen.name) {
            ReaderLoginScreen(navController = navController)
        }

        composable(ReaderScreens.SearchScreen.name) {
            val viewModel = hiltViewModel<ReaderBookSearchViewModel>()
            SearchScreen(navController = navController, viewModel = viewModel)
        }

        composable(ReaderScreens.SplashScreen.name) {
            ReaderSplashScreen(navController = navController)
        }

        composable(ReaderScreens.ReaderStatsScreen.name) {
            ReaderStatsScreen(navController = navController)
        }

        composable(ReaderScreens.UpdateScreen.name) {
            ReaderBookUpdateScreen(navController = navController)
        }

    }
}