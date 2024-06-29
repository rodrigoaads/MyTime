package com.rodrigoaads.mytime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rodrigoaads.mytime.ui.pages.RegisterPage
import com.rodrigoaads.mytime.ui.pages.TimePage

@Composable
fun MyTimeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = MyTimeDestination.Time.createRoute()
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = MyTimeDestination.Time.route) {
            TimePage(navController)
        }
        composable(
            route = "${MyTimeDestination.Register().route}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { navBackStackEntry ->
            RegisterPage(
                navController = navController,
                id = navBackStackEntry.arguments?.getInt("id") ?: 0
            )
        }
    }
}