package com.example.mobiletp.presenters

import android.os.Handler
import com.example.mobiletp.R
import com.example.mobiletp.views.LoginView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class LoginPresenter: MvpPresenter<LoginView>() {

    fun login(isSucces: Boolean) {

        viewState.startLoading()
        Handler().postDelayed({
            viewState.endLoadring()
            if (isSucces) {
                viewState.openDevices()
            } else {
                viewState.showError(R.string.something_went_wrong)
            }
        }, 500)
    }

}