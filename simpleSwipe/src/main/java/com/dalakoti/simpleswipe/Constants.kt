package com.dalakoti.simpleswipe

import android.content.res.Resources
import android.util.TypedValue

object Constants {
    const val iconOffsetMargin = 20
    const val cornerRadius = 10
    const val alphaAfterAction = 0.3f

    const val leftToRightSnapPercentage = 0.8
    const val rightToLeftSnapPercentage = 0.2
}

val Int.dpToPixel: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
