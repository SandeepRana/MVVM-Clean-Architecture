package com.example.myapplication.di

import com.example.myapplication.data.remote.CountryAPI
import com.example.myapplication.data.repository.CountryRepositoryImpl
import com.example.myapplication.domain.repository.CountryRepository
import com.example.myapplication.domain.usecase.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideEmployeeApi(): CountryAPI {
        return Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository(countryAPI: CountryAPI): CountryRepository {
        return CountryRepositoryImpl(countryAPI)
    }

    @Provides
    @Singleton
    fun provideEmployeeUseCase(countryRepository: CountryRepository): GetCountryUseCase {
        return GetCountryUseCase(countryRepository)
    }
}

fun getUnsafeOkHttpClient(): OkHttpClient {
    try {
        val trustAllCerts = arrayOf<TrustManager>(
            object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
                override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            }
        )

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .build()

    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}