package com.example.myapplication.data.repository

import android.util.Log
import com.example.myapplication.data.mapper.toCountry
import com.example.myapplication.data.mapper.toCountryList
import com.example.myapplication.data.remote.CountryAPI
import com.example.myapplication.domain.model.Country
import com.example.myapplication.domain.repository.CountryRepository
import com.example.myapplication.util.ApiResponse
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val countryAPI: CountryAPI) :
    CountryRepository {
    private val TAG = "CountryRepositoryImpl"

    override suspend fun getCountries(): ApiResponse<List<Country>> {
        try {
            val result = countryAPI.getCountries()
            val testingList = result+result+result+result+result
            return ApiResponse.SUCCESS(testingList.toCountryList())
        } catch (ex: Exception) {
            ex.printStackTrace()
            return ApiResponse.ERROR(ex.message.toString())
        }
    }

    override suspend fun getCountryDetail(name: String): ApiResponse<Country> {

        try {
            val result = countryAPI.getCountryDetail(name)
            return if (result.isNotEmpty()) {
                ApiResponse.SUCCESS(result[0].toCountry())
            } else {
                ApiResponse.ERROR("Result not found")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "getCountryDetail: ${ex.printStackTrace()}")
            return ApiResponse.ERROR(ex.message.toString())
        }
    }
}