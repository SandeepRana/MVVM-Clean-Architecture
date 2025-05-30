package com.example.myapplication.presentation.countryDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Country
import com.example.myapplication.domain.usecase.GetCountryDetailUseCase
import com.example.myapplication.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(private val getCountryDetailUseCase: GetCountryDetailUseCase) :
    ViewModel() {

    private val _countryDetail = MutableStateFlow<ApiResponse<Country>>(ApiResponse.LOADING())
    val countryDetail: StateFlow<ApiResponse<Country>>
        get() = _countryDetail

    fun getCountryDetail(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _countryDetail.emit(getCountryDetailUseCase.invoke(name))
        }
    }
}