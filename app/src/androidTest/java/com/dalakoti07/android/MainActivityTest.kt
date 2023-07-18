package com.dalakoti07.android

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.Swiper
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.util.HumanReadables
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class SwipeRightToLeftViewAction : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return ViewMatchers.isDisplayed()
    }

    override fun getDescription(): String {
        return "Swipe right to left"
    }

    override fun perform(uiController: UiController, view: android.view.View) {
        val parentView = view.parent as View
        val startCoordinates = floatArrayOf(parentView.width - 10f, view.y + view.height / 2f)
        val endCoordinates = floatArrayOf(10f, view.y + view.height / 2f)

//        val startCoordinates = floatArrayOf(view.width.toFloat() - 10f, view.height.toFloat() / 2f)
//        val endCoordinates = floatArrayOf(10f, view.height.toFloat() / 2f)

        try {
            val swiper = Swipe.FAST
            val precision = Press.FINGER.describePrecision()
            var status: Swiper.Status = Swiper.Status.FAILURE
            while (status != Swiper.Status.SUCCESS) {
                status = swiper.sendSwipe(uiController, startCoordinates, endCoordinates, precision)
                uiController.loopMainThreadForAtLeast(50)
            }
        } catch (re: RuntimeException) {
            throw PerformException.Builder()
                .withActionDescription(description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(re)
                .build()
        }
    }
}


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest{

    @get:Rule
    var splashActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun startActivity(){
        Thread.sleep(1000)
        Espresso.onView(
            ViewMatchers.withId(R.id.icIcon)
        ).perform(
            SwipeRightToLeftViewAction()
        )
        Thread.sleep(2000)
    }
}
