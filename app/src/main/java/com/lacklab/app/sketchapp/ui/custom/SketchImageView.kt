package com.lacklab.app.sketchapp.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.lacklab.app.sketchapp.R
import timber.log.Timber

class SketchImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {
    private lateinit var latestPaintPen: Paint
    private lateinit var latestPath: Path
    private var paintPenList = ArrayList<Paint>()
    private var pathList = ArrayList<Path>()

    companion object {
        const val STATE_STILL = 0;
        const val STATE_MOVE = 1;
        var DEFAULT_COLOR = 0;
    }

    private var lineWidth = 15F

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for(i in 0 until paintPenList.size) {
            canvas?.apply {
                drawPath(pathList[i], paintPenList[i])
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y
        Timber.d("x: $x, y: $y")
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startDraw(x!!, y!!)
            }
            MotionEvent.ACTION_MOVE -> {
                updateDraw(x!!, y!!)
            }
            MotionEvent.ACTION_UP ->{}
        }
        invalidate()
        return true
    }

    private fun initPaintPen() : Paint {
        val paint = Paint()
        with(paint) {
            strokeWidth = lineWidth
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.MITER
            strokeCap = Paint.Cap.ROUND
            color = ContextCompat.getColor(context, R.color.white)
        }
        return paint
    }

    private fun initPath() = Path()

    private fun initPen() {
        latestPaintPen =  initPaintPen()
        latestPath = initPath()
        paintPenList.add(latestPaintPen)
        pathList.add(latestPath)
    }

    private fun startDraw(x:Float, y:Float) {
        initPen()
        latestPath.moveTo(x, y)
    }

    private fun updateDraw(x:Float, y:Float) {
        latestPath.lineTo(x, y)
    }

    private fun endDraw(x:Float, y:Float) {

    }
}