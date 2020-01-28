package com.example.mobiletp.presenters

import com.example.mobiletp.R
import com.example.mobiletp.models.NewsModel
import com.example.mobiletp.providers.NewsProvider
import com.example.mobiletp.views.NewsView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class NewsPresenter: MvpPresenter<NewsView>() {

    fun loadNews() {
        viewState.startLoading()
        NewsProvider(this).loadNews()
    }

    fun newsLoaded(newsList: ArrayList<NewsModel> ) {
        viewState.endLoading()
        if (newsList.size == 0) {
            viewState.setupEmptyList()
            viewState.showError(R.string.friends_no_items)
        } else {
            viewState.setupNewsList(newsList)
        }
    }
}