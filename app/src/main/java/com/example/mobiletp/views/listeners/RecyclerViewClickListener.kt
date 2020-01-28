package com.example.mobiletp.views.listeners

import moxy.MvpView

interface RecyclerViewClickListener {

    fun onClick(view: MvpView, position: Int)
}