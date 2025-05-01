package ru.itis.android2sem.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.itis.android2sem.presentation.features.catdetail.CatDetailScreen
import ru.itis.android2sem.presentation.features.catlist.CatListScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.CatList.route
    ) {
        composable(Screen.CatList.route) {
            CatListScreen(navController = navController)
        }
        composable(
            route = Screen.CatDetail.route,
            arguments = listOf(navArgument("text") { type = NavType.StringType })
        ) { backStackEntry ->
            CatDetailScreen(
                text = backStackEntry.arguments?.getString("text") ?: "",
                navController = navController
            )
        }
    }
}