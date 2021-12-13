package com.lacklab.app.sketchapp.ui.view.sketch

import androidx.fragment.app.viewModels
import com.lacklab.app.sketchapp.R
import com.lacklab.app.sketchapp.base.BaseFragment
import com.lacklab.app.sketchapp.databinding.FragmentSketchBinding
import com.lacklab.app.sketchapp.ui.custom.SketchImageView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SketchFragment : BaseFragment<FragmentSketchBinding, SketchViewModel>() {

    private val sketchViewModel: SketchViewModel by viewModels()

    private lateinit var sketchImageView: SketchImageView

    override val layoutId: Int
        get() = R.layout.fragment_sketch

    override fun getVM() = sketchViewModel

    override fun bindVM(binding: FragmentSketchBinding, viewModel: SketchViewModel) {
        with(binding) {
            sketchImageView = sketchImageViewPaint
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }
}