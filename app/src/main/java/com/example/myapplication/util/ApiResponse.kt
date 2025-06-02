package com.example.myapplication.util

sealed class ApiResponse<T> {
    data class SUCCESS<T>(val data: T) : ApiResponse<T>()
    data class ERROR<T>(val error: String = "") : ApiResponse<T>()
    class LOADING<T> : ApiResponse<T>()
}