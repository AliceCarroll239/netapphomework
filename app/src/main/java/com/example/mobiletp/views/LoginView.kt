package com.example.mobiletp.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface LoginView: MvpView {

    fun startLoading()

    fun endLoadring()

    fun showError(messageResource: Int)

    fun openDevices()
}