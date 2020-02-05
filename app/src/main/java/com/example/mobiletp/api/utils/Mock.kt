package com.example.mobiletp.api.utils

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody


class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        val responseString = when {
            uri.endsWith("us") -> mockNews
            else -> ""
        }
        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    "application/json".toMediaType(),
                    responseString.toByteArray()
                )
            )
            .addHeader("content-type", "application/json")
            .build()
    }

}

const val mockNews = """
{
"status": "ok",
"totalResults": 1,
"articles": [
{
"source": 
{"id": "mock","name": "mock"},
"author": "Author",
"title": "mock",
"description": "mock",
"url": "https://www.nbcnews.com/news/us-news/fotis-dulos-charged-murder-missing-wife-jennifer-dulos-attempted-suicide-n1124706",
"urlToImage": "https://media3.s-nbcnews.com/j/newscms/2020_05/3189241/200116-fotis-dulos-al-0941_627e570cbdc01ef3fad8a6c2828228e1.nbcnews-fp-1200-630.jpg",
"publishedAt": "2020-01-28T18:28:00Z",
"content": "mock"}
]}
"""