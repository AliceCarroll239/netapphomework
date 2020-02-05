package com.example.mobiletp.api.utils

import com.example.mobiletp.configuration.GlobalParameters
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiBuilder {

    fun retrofitBuilder(baseURL: String): Retrofit {
        val client = OkHttpClient.Builder()
            .apply {
                if (GlobalParameters.mock) {
                    addInterceptor(MockInterceptor())
                }
            }
            .build()
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}