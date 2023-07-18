package com.dalakoti07.android

import android.R.color
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.core.view.isInvisible
import com.dalakoti07.android.databinding.LayoutSwipableButtonBinding


private const val TAG = "SwipableButton"

class SwipeButtonView(
    context: Context, attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding: LayoutSwipableButtonBinding =
        LayoutSwipableButtonBinding.inflate(LayoutInflater.from(context), this, true)

    private var isButtonPressed = false

    private var buttonWidth = 0
    private var buttonInitialOffsetFromLeft = 0
    private var lastTouchXCoordinate = 0f

    private var parentEndXCoordinate = 0

    private var isDisabled = false

    init {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.setColor(context.resources.getColor(R.color.colorPrimary)) // Set the desired color

        gradientDrawable.cornerRadius = 100f // Set the desired corner radius in pixels
        binding.llContainer.background = gradientDrawable
    }

    private fun checkIfCurrentXCoordinatesLiesInsideIcon(current: Float): Boolean{
        if(current>= buttonInitialOffsetFromLeft &&
            buttonInitialOffsetFromLeft + buttonWidth >= current ){
            return true
        }
        return false
    }

    // icons start at 97
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(isDisabled) return true
        val x = event.x
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                isButtonPressed = x >= 0 && checkIfCurrentXCoordinatesLiesInsideIcon(x)
            }
            MotionEvent.ACTION_MOVE -> {
                if (isButtonPressed) {
                    Log.d(TAG, "onTouchEvent: lastX = $lastTouchXCoordinate")
                    Log.d(TAG, "onTouchEvent x: $x")
                    Log.d(TAG, "parent end: $parentEndXCoordinate")

                    binding.icIcon.x = x
                    // if we are 80% there perform click
                    if (x >= parentEndXCoordinate*0.8) {
                        isButtonPressed = false
                        performClick()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                if (x >= parentEndXCoordinate*0.8) {
                    isButtonPressed = false
                    performClick()
                }else{
                    // todo set it to start
                    isButtonPressed = false
                    showInitialState()
                }
            }
        }
        lastTouchXCoordinate = x

        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        showFinalState()
        return true
    }

    private fun showInitialState(){
        binding.icIcon.x = buttonInitialOffsetFromLeft.toFloat()
        binding.centerText.isInvisible = false
        lastTouchXCoordinate= 0f
    }

    private fun showFinalState() {
        binding.icIcon.x = (parentEndXCoordinate - 100).toFloat()
        binding.centerText.isInvisible = true
        binding.llContainer.alpha = 0.9f
        isDisabled = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if(buttonInitialOffsetFromLeft == 0){
            buttonInitialOffsetFromLeft = binding.icIcon.x.toInt()
        }
        buttonWidth = binding.icIcon.width
        if(parentEndXCoordinate == 0){
            parentEndXCoordinate = binding.root.width
        }
    }

}