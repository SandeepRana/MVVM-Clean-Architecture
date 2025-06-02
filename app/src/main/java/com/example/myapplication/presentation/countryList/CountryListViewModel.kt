package com.example.myapplication.presentation.countryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Country
import com.example.myapplication.domain.usecase.GetCountryListUseCase
import com.example.myapplication.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val getCountryUseCase: GetCountryListUseCase) :
    ViewModel() {

    private val _countryList = MutableStateFlow<ApiResponse<List<Country>>>(ApiResponse.LOADING())
    val countryList: StateFlow<ApiResponse<List<Country>>>
        get() = _countryList

    init {
        getCountryList()
    }

    private fun getCountryList() {
        viewModelScope.launch(Dispatchers.IO) {
            _countryList.value = getCountryUseCase.invoke()
        }
    }
}