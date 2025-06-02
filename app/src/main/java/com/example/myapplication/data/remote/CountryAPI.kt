package com.example.myapplication.data.remote

import com.example.myapplication.data.model.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryAPI {

    @GET("v3.1/all")
    suspend fun getCountries(): List<Country>

    @GET("v3.1/name/{name}")
    suspend fun getCountryDetail(@Path("name") name: String): List<Country>

}