package com.example.mobiletp

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.screen.Screen.Companion.idle
import com.example.mobiletp.activities.NewsActivity
import com.example.mobiletp.configuration.GlobalParameters
import com.example.mobiletp.screens.NewsScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class NewsActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(NewsActivity::class.java)

    @Before
    fun setup() {
        GlobalParameters.mock = true
    }

    @Test
    fun testContentScreen() {
        Screen.onScreen<NewsScreen> {
            player {
                isVisible()
            }

            idle(2000)

            recycler {
                isDisplayed()
                hasSize(1)

                firstChild<NewsScreen.MainItem> {
                    isVisible()
                    title { hasText("mock") }
                }
            }
        }
    }
}