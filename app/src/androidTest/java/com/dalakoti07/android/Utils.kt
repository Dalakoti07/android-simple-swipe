package com.dalakoti07.android

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun swipeLeftOnView(): ViewAction {
    return actionWithAssertions(object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return allOf(isAssignableFrom(View::class.java))
        }

        override fun getDescription(): String {
            return "Swipe left on the view"
        }

        override fun perform(uiController: UiController, view: View) {
            val startCoordinates = CoordinatesProvider { floatArrayOf(110f, 1143f) }
            val endCoordinates = CoordinatesProvider { floatArrayOf(970f, 1143f) }

            val precision = Press.FINGER
            val swipe = GeneralSwipeAction(
                Swipe.FAST,
                startCoordinates,
                endCoordinates,
                precision
            )

            swipe.perform(uiController, view)
        }
    })
}

// Helper function to get the coordinates of the given view
private fun getCoordinates(view: View): CoordinatesProvider {
    val locationOnScreen = IntArray(2)
    view.getLocationOnScreen(locationOnScreen)

    val screenDensity = view.resources.displayMetrics.density
    val x = locationOnScreen[0] / screenDensity
    val y = locationOnScreen[1] / screenDensity

    return CoordinatesProvider { floatArrayOf(x, y) }
}





