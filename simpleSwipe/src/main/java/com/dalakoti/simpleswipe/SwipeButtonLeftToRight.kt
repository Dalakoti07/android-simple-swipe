package com.dalakoti.simpleswipe

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.core.view.isInvisible
import com.dalakoti.simpleswipe.databinding.LayoutSwipableLeftToRightBinding

private const val TAG = "SwipeButtonLeftToRight"

class SwipeButtonLeftToRight(
    context: Context, attrs: AttributeSet? = null,
) : FrameLayout(context, attrs) {

    private val binding: LayoutSwipableLeftToRightBinding =
        LayoutSwipableLeftToRightBinding.inflate(LayoutInflater.from(context), this, true)

    private var isButtonPressed = false

    private var buttonWidth = 0
    private var buttonInitialOffsetFromLeft = 0
    private var lastTouchXCoordinate = 0f

    private var parentTotalWidth = 0

    private var isDisabled = false

    init {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.SwipeButtonLeftToRight)
        val primaryColor = typedArray.getColor(R.styleable.SwipeButtonLeftToRight_primaryColorLR,resources.getColor(R.color.colorPrimaryDark))
        val secondaryColor = typedArray.getColor(R.styleable.SwipeButtonLeftToRight_secondaryColorLR,resources.getColor(R.color.colorPrimary))
        val centerText = typedArray.getString(R.styleable.SwipeButtonLeftToRight_centerTextLR)

        // set the values from style attrs
        gradientDrawable.setColor(secondaryColor)
        binding.centerText.setTextColor(primaryColor)
        binding.centerText.text = centerText
        gradientDrawable.cornerRadius = Constants.cornerRadius // Set the desired corner radius in pixels
        binding.llContainer.background = gradientDrawable
        binding.icIcon.backgroundTintList = ColorStateList.valueOf(primaryColor)

        typedArray.recycle()
    }

    private fun checkIfCurrentXCoordinatesLiesInsideIcon(current: Float): Boolean{
        if(current>= buttonInitialOffsetFromLeft &&
            buttonInitialOffsetFromLeft + buttonWidth >= current ){
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

                    binding.icIcon.x = x
                    changeAlphaAsPerXValue(x)
                    // if we are 80% there perform click
                    if (x >= parentTotalWidth * Constants.leftToRightSnapPercentage) {
                        isButtonPressed = false
                        performClick()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                if (x >= parentTotalWidth * Constants.leftToRightSnapPercentage) {
                    isButtonPressed = false
                    performClick()
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
        if(percentageOfWithCovered<0.4){
            binding.centerText.isInvisible = false
            binding.centerText.alpha = (0.5-(percentageOfWithCovered)).toFloat()
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
        binding.icIcon.x = buttonInitialOffsetFromLeft.toFloat()
        binding.centerText.isInvisible = false
        lastTouchXCoordinate= 0f
        binding.centerText.alpha = 1f
    }

    private fun showFinalState() {
        binding.icIcon.x = (parentTotalWidth - buttonWidth - Constants.iconOffsetMargin).toFloat()
        binding.centerText.isInvisible = false
        // just to give disabledEffect
        binding.llContainer.alpha = 0.9f
        isDisabled = true
        binding.centerText.alpha = Constants.alphaAfterAction
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout: ...")
        if(buttonInitialOffsetFromLeft == 0){
            buttonInitialOffsetFromLeft = binding.icIcon.x.toInt()
        }
        buttonWidth = binding.icIcon.width
        if(parentTotalWidth == 0){
            parentTotalWidth = binding.root.width
        }
        // onMeasure: icon start x: 55.0 icon end x: 970
        val intArr = intArrayOf(0,0)
        binding.icIcon.getLocationOnScreen(intArr)
        Log.d(TAG, "onLayout: icon start x: ${binding.icIcon.x} icon end x: $parentTotalWidth")
        Log.d(TAG, "onLayout: icon y: ${binding.icIcon.y}")
        // 110 and 1143
        Log.d(TAG, "onLayout: ${intArr[0]} and ${intArr[1]}")
    }

}