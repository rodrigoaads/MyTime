package com.rodrigoaads.mytime.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rodrigoaads.mytime.ui.pages.register.RegisterPage
import com.rodrigoaads.mytime.ui.pages.time.TimePage

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
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { navBackStackEntry ->
            RegisterPage(
                navController = navController,
                id = navBackStackEntry.arguments?.getString("id") ?: ""
            )
        }
    }
}