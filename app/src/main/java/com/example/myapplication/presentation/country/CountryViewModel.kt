package com.example.myapplication.presentation.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Country
import com.example.myapplication.domain.usecase.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val getCountryUseCase: GetCountryUseCase) :
    ViewModel() {

    private var _countryList = MutableStateFlow<List<Country>>(emptyList())
    val employeeList: StateFlow<List<Country>>
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