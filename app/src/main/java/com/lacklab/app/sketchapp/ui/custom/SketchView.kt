package com.lacklab.app.sketchapp.ui.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import androidx.core.content.ContextCompat
import com.lacklab.app.sketchapp.R
import timber.log.Timber
import com.lacklab.app.sketchapp.ui.custom.SingletonTest

class SketchView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : View(context, attrs, defStyle), SurfaceHolder.Callback {
    private lateinit var latestPaintPen: Paint
    private lateinit var latestPath: Path
    private var paintPenList = ArrayList<Paint>()
    private var pathList = ArrayList<Path>()

    companion object {
        const val STATE_STILL = 0;
        const val STATE_MOVE = 1;
        var DEFAULT_COLOR = 0;
    }

    init {
//        holder.addCallback(this)
    }

    private var lineWidth = 15F

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Timber.d("onAttachedToWindow")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Timber.d("onMeasure")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Timber.d("onLayout")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Timber.d("onDraw")
        drawPath(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        val x = event?.x
        val y = event?.y
        Timber.d("onTouchEvent x: $x, y: $y")
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startPath(x!!, y!!)
            }
            MotionEvent.ACTION_MOVE -> {
                updatePath(x!!, y!!)
//                val canvas = holder.lockCanvas()
//                drawPath(canvas)
//                holder.unlockCanvasAndPost(canvas)
                invalidate()
            }
            MotionEvent.ACTION_UP ->{

            }
        }
//        return true
        // if fragment have View.OnClickListener, it will be pass to onClick()
        return super.onTouchEvent(event)
    }

    private fun initPaintPen() : Paint {
        val paint = Paint()
        with(paint) {
            strokeWidth = lineWidth
            isAntiAlias = true
            isDither = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
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

    private fun startPath(x:Float, y:Float) {
        initPen()
        latestPath.moveTo(x, y)
    }

    private fun updatePath(x:Float, y:Float) {
        latestPath.lineTo(x, y)
    }

    private fun endPath(x:Float, y:Float) {

    }

    private fun drawPath(canvas: Canvas?) {
        for(i in 0 until paintPenList.size) {
            canvas?.apply {
                drawPath(pathList[i], paintPenList[i])
            }
        }
    }

    private fun cleanDraw() {

    }

    private fun undoDraw() {

    }

    private fun resetPen(){

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Timber.d("surfaceCreated")
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        Timber.d("surfaceChanged")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        Timber.d("surfaceDestroyed")
    }
}