package com.example.myapplication.presentation.countryDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.nav.Screen
import com.example.myapplication.presentation.country.Loading
import com.example.myapplication.util.ApiResponse

@Composable
fun CountryDetailScreen(
    innerPadding: PaddingValues,
    navController: NavController,
    viewModel: CountryDetailViewModel = hiltViewModel(),
) {
    val name = navController.previousBackStackEntry?.savedStateHandle?.get<String>(Screen.NAME)
    name?.let { viewModel.getCountryDetail(name) }
    val result = viewModel.countryDetail.collectAsState().value
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        when (result) {
            is ApiResponse.ERROR -> {

            }

            is ApiResponse.LOADING -> {
                Loading(innerPadding = innerPadding)
            }

            is ApiResponse.SUCCESS -> {
                val countryDetail = result.data
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(5.dp),
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        onClick = {
//            Toast.makeText(context, country.name.common, Toast.LENGTH_SHORT).show()
                        }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Text(
                                    fontSize = 100.sp,
                                    text = countryDetail.flag,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                                Text(
                                    text = countryDetail.name.common,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 40.sp
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}