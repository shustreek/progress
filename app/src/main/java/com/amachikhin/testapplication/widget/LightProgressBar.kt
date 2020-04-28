package com.amachikhin.testapplication.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.amachikhin.testapplication.R
import kotlin.math.min


class LightProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val progressPaint: Paint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
    }

    private val textPaint = Paint().apply {
        textAlign = Paint.Align.LEFT
        isFakeBoldText = true
    }

    private var indicatorHeight = 0

    private var filledSectionColor = 0

    private var unfilledSectionColor = 0

    private var barHeight = 0

    var progress: Int = 0
        set(value) {
            field = min(value, 100)
            invalidate()
        }

    init {

        val typedArray =
            getContext().theme.obtainStyledAttributes(attrs, R.styleable.LightProgressBar, defStyleAttr, defStyleRes)
        textPaint.textSize =
            typedArray.getInt(R.styleable.LightProgressBar_lpb_text_size, getDimen(R.dimen.lpb_text_size)).toFloat()

        indicatorHeight =
            typedArray.getInt(R.styleable.LightProgressBar_lpb_indicator_height, getDimen(R.dimen.lpb_indicator_height))
        barHeight = typedArray.getInt(R.styleable.LightProgressBar_lpb_bar_height, getDimen(R.dimen.lpb_bar_height))

        filledSectionColor =
            typedArray.getColor(R.styleable.LightProgressBar_lpb_filled_section_color, getColor(R.color.lpb_filled_section_color))
        unfilledSectionColor =
            typedArray.getColor(
                R.styleable.LightProgressBar_lpb_unfilled_section_color,
                getColor(R.color.lpb_unfilled_section_color)
            )
        textPaint.color = typedArray.getColor(R.styleable.LightProgressBar_lpb_text_color, getColor(R.color.lpb_text_color))

        progress = typedArray.getInt(R.styleable.LightProgressBar_lpb_progress, 0)

        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val height: Int
        height = when (MeasureSpec.getMode(heightMeasureSpec)) {
            MeasureSpec.EXACTLY -> {
                heightSize
            }
            MeasureSpec.AT_MOST ->
                min(indicatorHeight, heightSize)
            else ->
                indicatorHeight
        }
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        val halfHeight = height / 2f
        val progressEndX = (width * progress / 100f)

        progressPaint.strokeWidth = barHeight.toFloat()
        progressPaint.color = filledSectionColor
        canvas.drawLine(0f, halfHeight, progressEndX, halfHeight, progressPaint)

        progressPaint.color = unfilledSectionColor
        canvas.drawLine(progressEndX, halfHeight, width.toFloat(), halfHeight, progressPaint)

        val descent = textPaint.descent()
        val text = "$progress%"
        val textWidth = textPaint.measureText(text)
        val xPos = with(progressEndX + descent) {
            if (this + textWidth >= width) width - textWidth - descent else this
        }
        val yPos = halfHeight - (descent + textPaint.ascent()) / 2
        canvas.drawText(text, xPos, yPos, textPaint)

    }

    private fun getColor(@ColorRes resId: Int) = ContextCompat.getColor(context, resId)

    private fun getDimen(@DimenRes resId: Int) = resources.getDimensionPixelSize(resId)
}