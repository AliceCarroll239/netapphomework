package com.example.mobiletp.api.interfaces

import com.example.mobiletp.api.dao.NewsDAO
import retrofit2.Call
import retrofit2.http.GET

interface NewsGetApi {
    @GET("top-headlines?apiKey=67ccbf654c29464c90cce0047abea236&country=us")
    fun getNews(): Call<NewsDAO>
}