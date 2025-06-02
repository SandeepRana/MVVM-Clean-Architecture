package com.example.myapplication.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.countryList.CountryScreen
import com.example.myapplication.presentation.countryDetail.CountryDetailScreen

@Composable
fun NavGraph(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.CountryList.route) {

        composable(route = Screen.CountryList.route) {
            CountryScreen(innerPadding = innerPadding) {
                navController.currentBackStackEntry?.savedStateHandle?.set(Screen.NAME, it)
                navController.navigate(route = Screen.CountryDetail.route)
            }
        }

        composable(route = Screen.CountryDetail.route) {
            CountryDetailScreen(innerPadding = innerPadding, navController)
        }

    }
}