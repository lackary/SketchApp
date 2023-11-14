package com.lacklab.app.sketchapp.ui.view.sketch

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.viewModels
import com.lacklab.app.sketchapp.R
import com.lacklab.app.sketchapp.base.BaseFragment
import com.lacklab.app.sketchapp.databinding.FragmentSketchBinding
import com.lacklab.app.sketchapp.ui.custom.SketchView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*
import java.util.concurrent.Executor

@AndroidEntryPoint
class SketchFragment :
    BaseFragment<FragmentSketchBinding, SketchViewModel>(),
    View.OnTouchListener, View.OnClickListener {
    var queue:Queue<String> = LinkedList()

    private val sketchViewModel: SketchViewModel by viewModels()

    private lateinit var sketchView: SketchView

    override val layoutId: Int
        get() = R.layout.fragment_sketch

    override fun getVM() = sketchViewModel

    override fun bindVM(binding: FragmentSketchBinding, viewModel: SketchViewModel) {
        with(binding) {
            sketchView = sketchViewPaint
            val message = Message()

            sketchView.setOnTouchListener { v, event ->
                Timber.d("onTouch")
                when(event.action) {
                    MotionEvent.ACTION_DOWN -> {
                    }
                    MotionEvent.ACTION_MOVE -> {
                    }
                    MotionEvent.ACTION_UP ->{}
                }
                false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
        sketchView.setOnTouchListener(this)
        sketchView.setOnClickListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        Timber.d("onTouch")
        return false
    }

    override fun onClick(v: View?) {
        Timber.d("onClick")
    }
}