package com.example.mobiletp.views

import com.example.mobiletp.api.dao.NewsDAO
import com.example.mobiletp.models.NewsModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface NewsView: MvpView {

    fun showError(messageResource: Int)

    fun setupEmptyList()

    fun setupNewsList(newsList: List<NewsModel>)

    fun startLoading()

    fun endLoading()
}