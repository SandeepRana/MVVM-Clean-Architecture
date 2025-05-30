package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.repository.CountryRepository
import javax.inject.Inject

class GetCountryUseCase @Inject constructor (private val countryRepository: CountryRepository) {

    suspend operator fun invoke() = countryRepository.getCountry()
}