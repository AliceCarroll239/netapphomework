package com.example.mobiletp.screens

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.example.mobiletp.R

open class MainActivityScreen : Screen<MainActivityScreen>() {
    val button: KButton = KButton { withId(R.id.btn_login) }
}