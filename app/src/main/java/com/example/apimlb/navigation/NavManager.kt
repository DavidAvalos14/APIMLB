package com.example.apimlb.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apimlb.view.HomeView
import com.example.apimlb.view.SplashScreen
import com.example.apimlb.view.JuegosView
import com.example.apimlb.view.StandingsView

@Composable
fun NavManager(){
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "Splash"){
        composable("Home") {
            HomeView(navController)
        }
        composable ( route = "Juegos",
            //arguments = listOf(navArgument("id") {type = NavType.IntType})
        ) { backStackEntry ->
            //val id = backStackEntry.arguments?.getInt("id") ?: -1
            JuegosView(navController)
        }
        composable ( route = "Standings",
            //arguments = listOf(navArgument("id") {type = NavType.IntType})
        ) { backStackEntry ->
            //val id = backStackEntry.arguments?.getInt("id") ?: -1
            StandingsView(navController)
        }
        composable( route = "Splash" ) {
            SplashScreen(navController)
        }
    }
}