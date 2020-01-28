package com.example.mobiletp.activities

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletp.R
import com.example.mobiletp.adapters.NewsAdapter
import com.example.mobiletp.models.NewsModel
import com.example.mobiletp.presenters.NewsPresenter
import com.example.mobiletp.views.NewsView
import com.github.rahatarmanahmed.cpv.CircularProgressView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class NewsActivity : MvpAppCompatActivity(), NewsView {

    override fun setupNewsList(newsList: List<NewsModel>) {
        mRVDevices.visibility = View.VISIBLE
        mTxtNoDevices.visibility = View.GONE

        mAdapter.setupDevices(newsList)
    }

    @InjectPresenter
    lateinit var devicePresenter: NewsPresenter

    private lateinit var mAdapter: NewsAdapter
    private lateinit var mCpvWait: CircularProgressView
    private lateinit var mTxtNoDevices: TextView
    private lateinit var mRVDevices: RecyclerView

    override fun showError(messageResource: Int) {
        mTxtNoDevices.text = getString(messageResource)
    }

    override fun setupEmptyList() {
        mRVDevices.visibility = View.GONE
        mTxtNoDevices.visibility = View.VISIBLE
    }

    override fun startLoading() {
        mCpvWait.visibility = View.VISIBLE
        mRVDevices.visibility = View.GONE
        mTxtNoDevices.visibility = View.GONE
    }

    override fun endLoading() {
        mCpvWait.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_devices)

        mRVDevices = findViewById(R.id.recycler_devices)
        mTxtNoDevices = findViewById(R.id.txt_devices_no_items)
        mCpvWait = findViewById(R.id.cpv_devices_progress_view)

        devicePresenter.loadNews()
        mAdapter = NewsAdapter()

        mRVDevices.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
        }
    }
}
