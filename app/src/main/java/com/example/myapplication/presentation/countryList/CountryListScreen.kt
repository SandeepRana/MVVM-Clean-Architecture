package com.example.myapplication.presentation.countryList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.data.model.Country
import com.example.myapplication.util.ApiResponse

@Composable
fun CountryListScreen(
    innerPadding: PaddingValues,
    onClick: (String) -> Unit,
    viewModel: CountryListViewModel = hiltViewModel()
) {
    val countryList = viewModel.countryList.collectAsState().value

    when (countryList) {
        is ApiResponse.LOADING -> {
            Loading(innerPadding)
        }

        is ApiResponse.ERROR -> {}
        is ApiResponse.SUCCESS -> {
            ShowCountries(innerPadding, countryList.data, onClick)
        }
    }
}

@Composable
fun ShowCountries(innerPadding: PaddingValues, data: List<Country>, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(data) {
                CountryCardItem(country = it, onClick = onClick)
            }
        }
    }
}

@Composable
fun Loading(innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun CountryCardItem(country: Country, onClick: (String) -> Unit) {
    val context = LocalContext.current
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        onClick = {
            onClick(country.name.common)
//            Toast.makeText(context, country.name.common, Toast.LENGTH_SHORT).show()
        }
    ) {
        Row(Modifier.padding(10.dp)) {
            Text(
                text = country.name.common,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(text = country.flag, modifier = Modifier.padding(start = 10.dp))
        }
    }
}