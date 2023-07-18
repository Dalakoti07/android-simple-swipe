package com.dalakoti07.android

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest{

    @get:Rule
    var splashActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun startActivity(){
        Thread.sleep(1000)
        Espresso.onView(
            ViewMatchers.withId(R.id.swipeButton)
        ).perform(
            swipeLeftOnView()
        )
        Thread.sleep(4000)
    }
}
