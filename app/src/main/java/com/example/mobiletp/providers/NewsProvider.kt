package com.example.mobiletp.providers

import android.os.Handler
import com.example.mobiletp.api.dao.NewsDAO
import com.example.mobiletp.api.interfaces.NewsGetApi
import com.example.mobiletp.api.utils.ApiBuilder
import com.example.mobiletp.configuration.GlobalParameters
import com.example.mobiletp.models.NewsModel
import com.example.mobiletp.presenters.NewsPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException

class NewsProvider(var presenter: NewsPresenter) {

    fun loadNews() {
        Handler().postDelayed({
            val newsList: ArrayList<NewsModel> = ArrayList()
            runBlocking {
                getNews()?.forEach {
                newsList.add(
                    NewsModel(
                        title = it.title!!,
                        urlToImage = imageChecker(it.urlToImage),
                        publishedAt = it.publishedAt!!,
                        likeCount = (0..100).random(),
                        liked = false,
                        content = contentChecker(it.content),
                        commentCount = (0..100).random(),
                        shareCount = (0..100).random()
                    ))
                }
            }
            presenter.newsLoaded(newsList)
        },2000)
    }

    suspend fun getNews(): List<NewsDAO.Article>?  = withContext(Dispatchers.IO) {
        val request = ApiBuilder().retrofitBuilder(GlobalParameters.backendURL)
            .create(NewsGetApi::class.java).getNews()
        try {
            val response = request.execute()
            return@withContext if (response.code() == 200) {
                response.body()!!.articles
            } else null
        } catch (e: IOException) {
            return@withContext null
        }
    }

    fun imageChecker(urlToImage: String?): String {
        if (urlToImage == null) {
            return "https://legacytaylorsville.com/wp-content/uploads/2015/07/No-Image-Available1.png"
        } else return urlToImage
    }

    fun contentChecker(content: String?): String {
        if (content == null) {
            return "NO DESCRIPTION AVAILABLE"
        } else return content
    }
}