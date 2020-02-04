package com.example.mobiletp.models

import android.net.Uri

class NewsModel(
    var title: String,
    var urlToImage: String,
    var publishedAt: String,
    var likeCount: Int,
    var content: String,
    var liked: Boolean,
    var commentCount: Int,
    var shareCount: Int,
    var needMap: Boolean,
    var location: Uri?
)