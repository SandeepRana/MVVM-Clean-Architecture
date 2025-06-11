package com.example.myapplication.data.mapper

import com.example.myapplication.data.model.CountryDto
import com.example.myapplication.domain.model.Country

fun CountryDto.toCountry(): Country = Country(
    commonName = this.name.common,
    official = this.name.official,
    flag = flag
)

fun List<CountryDto>.toCountryList(): List<Country> = this.map { it.toCountry() }
