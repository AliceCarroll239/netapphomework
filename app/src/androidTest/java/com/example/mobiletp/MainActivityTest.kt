package com.example.mobiletp

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
import com.example.mobiletp.activities.MainActivity
import com.example.mobiletp.screens.MainActivityScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testContentScreen() {
        Screen.onScreen<MainActivityScreen> {
            button {
                isVisible()
                hasText("START")
                click()
            }
        }
    }
}
