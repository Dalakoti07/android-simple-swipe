package com.dalakoti.simpleswipe

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import androidx.core.view.isInvisible
import com.dalakoti.simpleswipe.databinding.LayoutSwipableRightToLeftBinding

private const val TAG = "SwipeButtonRightToLeft"

class SwipeButtonRightToLeft(
    context: Context, attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding: LayoutSwipableRightToLeftBinding =
        LayoutSwipableRightToLeftBinding.inflate(LayoutInflater.from(context), this, true)

    private var isButtonPressed = false

    private var buttonWidth = 0
    private var buttonInitialOffsetFromParentEnd = 0
    private var lastTouchXCoordinate = 0f

    private var parentTotalWidth = 0
    private var lastXCoordinateOfIcon = 0f

    private var isDisabled = false

    init {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.SwipeButtonRightToLeft)
        val primaryColor = typedArray.getColor(R.styleable.SwipeButtonRightToLeft_primaryColorRL,resources.getColor(R.color.colorPrimaryDark))
        val secondaryColor = typedArray.getColor(R.styleable.SwipeButtonRightToLeft_secondaryColorRL,resources.getColor(R.color.colorPrimary))
        val centerText = typedArray.getString(R.styleable.SwipeButtonRightToLeft_centerTextRL)

        // set the values from style attrs
        gradientDrawable.setColor(secondaryColor)
        binding.centerText.setTextColor(primaryColor)
        binding.centerText.text = centerText
        gradientDrawable.cornerRadius = Constants.cornerRadius.dpToPixel.toFloat() // Set the desired corner radius in pixels
        binding.llContainer.background = gradientDrawable
        binding.icIcon.backgroundTintList = ColorStateList.valueOf(primaryColor)

        typedArray.recycle()
    }

    private fun checkIfCurrentXCoordinatesLiesInsideIcon(current: Float): Boolean{
        if(current>= buttonInitialOffsetFromParentEnd &&
            buttonInitialOffsetFromParentEnd + buttonWidth >= current ){
            return true
        }
        return false
    }

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
                    Log.d(TAG, "parent end: $parentTotalWidth")

                    lastXCoordinateOfIcon = x
                    // only if we touch before button starts then only change x
                    if(x <= buttonInitialOffsetFromParentEnd){
                        binding.icIcon.x = x
                        changeAlphaAsPerXValue(x)
                    }
                    // view is swipe to 10% or below on horizontal axis
                    if (x <= parentTotalWidth * Constants.rightToLeftSnapPercentage) {
                        if(isButtonPressed){
                            isButtonPressed = false
                            performClick()
                        }
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                if (x <= parentTotalWidth * Constants.rightToLeftSnapPercentage) {
                    // view is swipe to 10% or below on horizontal axis
                    if(isButtonPressed){
                        isButtonPressed = false
                        performClick()
                    }
                }else{
                    isButtonPressed = false
                    showInitialState()
                }
            }
        }
        lastTouchXCoordinate = x

        return true
    }

    private fun changeAlphaAsPerXValue(x: Float) {
        val percentageOfWithCovered = x / parentTotalWidth
        if(percentageOfWithCovered>0.1){
            binding.centerText.isInvisible = false
            binding.centerText.alpha = percentageOfWithCovered
        }else{
            binding.centerText.isInvisible = true
        }
    }

    override fun performClick(): Boolean {
        super.performClick()
        showFinalState()
        return true
    }

    private fun showInitialState(){
        val finalX = buttonInitialOffsetFromParentEnd.toFloat()
        val iconAnimation = TranslateAnimation(
            0f,
            (parentTotalWidth - lastXCoordinateOfIcon - buttonWidth - Constants.iconOffsetMargin),
            0f,
            0f,
        ).apply{
            duration = Constants.animationDuration
            isFillEnabled = true
            setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    Log.d(TAG, "onAnimationStart .....")
                }

                override fun onAnimationEnd(animation: Animation?) {
                    Log.d(TAG, "onAnimationEnd .....")
                    binding.icIcon.x = finalX
                    binding.centerText.isInvisible = false
                    lastTouchXCoordinate= 0f
                    binding.centerText.alpha = 1f
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }
        binding.icIcon.startAnimation(iconAnimation)
    }

    private fun showFinalState() {
        Log.d(TAG, "showFinalState .... $lastXCoordinateOfIcon")
        val finalX = (Constants.iconOffsetMargin).toFloat()
        val iconAnimation = TranslateAnimation(
            0f,
            -(lastXCoordinateOfIcon),
            0f,
            0f,
        ).apply{
            duration = Constants.animationDuration
            isFillEnabled = true
            setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    Log.d(TAG, "onAnimationStart .....")
                }

                override fun onAnimationEnd(animation: Animation?) {
                    Log.d(TAG, "onAnimationEnd .....")
                    binding.icIcon.x = finalX
                    binding.centerText.isInvisible = false
                    // just to give disabledEffect
                    binding.llContainer.alpha = 0.9f
                    isDisabled = true
                    binding.centerText.alpha = Constants.alphaAfterAction
                }

                override fun onAnimationRepeat(animation: Animation?) {}
            })
        }
        binding.icIcon.startAnimation(iconAnimation)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if(buttonInitialOffsetFromParentEnd == 0){
            buttonInitialOffsetFromParentEnd = binding.icIcon.x.toInt()
        }
        buttonWidth = binding.icIcon.width
        if(parentTotalWidth == 0){
            parentTotalWidth = binding.root.width
        }
        // onMeasure: icon start x: 55.0 icon end x: 970
        val intArr = intArrayOf(0,0)
        binding.icIcon.getLocationOnScreen(intArr)
        Log.d(TAG, "offset: $buttonInitialOffsetFromParentEnd")
        Log.d(TAG, "onLayout: icon start x: ${binding.icIcon.x} icon end x: $parentTotalWidth")
        Log.d(TAG, "onLayout: icon y: ${binding.icIcon.y}")
        // 110 and 1143
        Log.d(TAG, "onLayout: ${intArr[0]} and ${intArr[1]}")
    }

}