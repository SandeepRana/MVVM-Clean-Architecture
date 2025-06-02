package com.example.myapplication.domain.repository

import com.example.myapplication.data.model.Country
import com.example.myapplication.util.ApiResponse

interface CountryRepository {
    suspend fun getCountries(): ApiResponse<List<Country>>

    suspend fun getCountryDetail(name: String): ApiResponse<Country>
}