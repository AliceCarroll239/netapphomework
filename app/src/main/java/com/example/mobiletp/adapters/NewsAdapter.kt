package com.example.mobiletp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletp.R
import com.example.mobiletp.models.NewsModel
import com.example.mobiletp.utils.Utils
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class NewsAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mNewsList: ArrayList<NewsModel> = ArrayList()

    fun setupDevices(newsList: List<NewsModel>) {
        mNewsList.clear()
        mNewsList.addAll(newsList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mNewsList.count()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DevicesViewHolder) {
            holder.bind(mNewsList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cell_device, parent, false )
        return DevicesViewHolder(itemView, context)
    }

    class DevicesViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {

        private var mNewsPosted: TextView = itemView.findViewById(R.id.news_posted)
        private var mNewsImg: CircleImageView = itemView.findViewById(R.id.news_img)
        private var mNewsTitle: TextView = itemView.findViewById(R.id.news_title)
        private var mNewsDescr: TextView = itemView.findViewById(R.id.news_description)
        private var mNewsLike: ImageView = itemView.findViewById(R.id.likes)
        private var mNewsLikeCount: TextView = itemView.findViewById(R.id.likescount)
        private var mNewsCommentCount: TextView = itemView.findViewById(R.id.commentCount)
        private var mNewsShareCount: TextView = itemView.findViewById(R.id.shareCount)
        private var mLocation: ImageView = itemView.findViewById(R.id.location)

        @SuppressLint("SetTextI18n")
        fun bind(article: NewsModel) {
            Picasso.with(itemView.context).load(article.urlToImage).into(mNewsImg)

            mNewsPosted.text = Utils().clearDateFormat(article.publishedAt)
            mNewsTitle.text = "${article.title}"
            mNewsDescr.text = "${article.content}".take(240).substringBeforeLast(".") + ".  ..."
            mNewsLikeCount.text = "${article.likeCount}"
            mNewsCommentCount.text = "${article.commentCount}"
            mNewsShareCount.text = "${article.shareCount}"

            if (article.likeCount == 0) {
                mNewsLikeCount.text = ""
            } else {
                mNewsLikeCount.text = "${article.likeCount}"
            }

            mNewsLike.setOnClickListener {
                updateLike(article)
            }

            if (!article.needMap) {
                mLocation.visibility = View.GONE
            }

            mLocation.setOnClickListener {
                showMap()
            }
        }

        fun updateLike(newsModel: NewsModel) {
            if (!newsModel.liked) {
                mNewsLike.setColorFilter(Color.RED)
                newsModel.likeCount = newsModel.likeCount.inc()
                mNewsLikeCount.text = newsModel.likeCount.toString()
                newsModel.liked = true
            } else {
                mNewsLike.setColorFilter(Color.WHITE)
                newsModel.likeCount = newsModel.likeCount.dec()
                mNewsLikeCount.text = newsModel.likeCount.toString()
                newsModel.liked = false
            }
        }

        fun showMap() {
            val location = Utils().getRandomLocation(37.7749, -122.4194, 10000)
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(context, mapIntent, null)
        }
    }
}