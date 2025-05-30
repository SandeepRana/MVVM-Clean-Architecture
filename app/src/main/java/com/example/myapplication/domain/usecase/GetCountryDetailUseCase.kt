package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountryDetailUseCase @Inject constructor(private val countryRepository: CountryRepository) {

    suspend fun invoke(name: String) = countryRepository.getCountryDetail(name)
}