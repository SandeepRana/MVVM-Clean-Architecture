package com.example.myapplication.nav

sealed class Screen(val route: String) {
    data object CountryList : Screen("CountryList")
    data object CountryDetail : Screen("CountryDetail")

    companion object {
        const val NAME = "Name"
    }
}