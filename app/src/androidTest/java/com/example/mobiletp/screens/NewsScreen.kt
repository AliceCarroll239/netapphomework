package com.example.mobiletp.screens

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.example.mobiletp.R
import org.hamcrest.Matcher

class NewsScreen : Screen<NewsScreen>() {
    val player: KView = KView { withId(R.id.player_view) }
    val recycler: KRecyclerView = KRecyclerView({ withId(R.id.recycler_devices) },
        itemTypeBuilder = {
            itemType(::MainItem)
        })

    class MainItem(parent: Matcher<View>) : KRecyclerItem<MainItem>(parent) {
        val title: KTextView = KTextView(parent) { withId(R.id.news_title) }
    }
}