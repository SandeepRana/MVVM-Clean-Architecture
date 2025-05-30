package com.example.myapplication.data.model

data class Country(
    val name: Name = Name(),
    val flag: String = "",
)

data class Name(
    val common: String = "",
    val official: String = ""
)
