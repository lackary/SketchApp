package com.lacklab.app.sketchapp.ui.view.sketch

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.viewModels
import com.lacklab.app.sketchapp.R
import com.lacklab.app.sketchapp.base.BaseFragment
import com.lacklab.app.sketchapp.databinding.FragmentSketchBinding
import com.lacklab.app.sketchapp.ui.custom.SketchView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SketchFragment :
    BaseFragment<FragmentSketchBinding, SketchViewModel>(){

    private val sketchViewModel: SketchViewModel by viewModels()

    private lateinit var sketchView: SketchView

    override val layoutId: Int
        get() = R.layout.fragment_sketch

    override fun getVM() = sketchViewModel

    override fun bindVM(binding: FragmentSketchBinding, viewModel: SketchViewModel) {
        with(binding) {
            sketchView = sketchViewPaint
//            sketchImageView.setOnTouchListener { v, event ->
//                Timber.d("onTouch")
//                when(event.action) {
//                    MotionEvent.ACTION_DOWN -> {
//                    }
//                    MotionEvent.ACTION_MOVE -> {
//                    }
//                    MotionEvent.ACTION_UP ->{}
//                }
//                false
//            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
//        sketchView.setOnTouchListener(this)
    }

//    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//        Timber.d("onTouch")
//        return false
//    }
}