package com.example.mobiletp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.mobiletp.R
import com.example.mobiletp.presenters.LoginPresenter
import com.example.mobiletp.views.LoginView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(), LoginView  {

    private lateinit var mCpvBar: CircularProgressView
    private lateinit var mBtnLogin: Button
    private lateinit var mTxtHello: TextView

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTxtHello = findViewById(R.id.tx_login_hello)
        mBtnLogin = findViewById(R.id.btn_login)
        mCpvBar = findViewById(R.id.progress_view)

        mBtnLogin.setOnClickListener{
            loginPresenter.login(true)
        }
    }

    override fun startLoading() {
        mBtnLogin.visibility = View.GONE
        mCpvBar.visibility = View.VISIBLE
    }

    override fun endLoadring() {
        mBtnLogin.visibility = View.VISIBLE
        mCpvBar.visibility = View.GONE
    }

    override fun showError(message: Int) {
       Toast.makeText(applicationContext, getString(message), Toast.LENGTH_SHORT).show()
    }

    override fun openDevices() {
        startActivity(Intent(applicationContext, NewsActivity::class.java))
    }
}
