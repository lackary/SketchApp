package com.lacklab.app.sketchapp.ui.view.sketch

import android.graphics.Paint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lacklab.app.sketchapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SketchViewModel @Inject constructor() : BaseViewModel() {

    private lateinit var _pen: MutableLiveData<Paint>
    val paint: LiveData<Paint>
        get() = _pen

    init {

    }

    private fun initPen() {
        _pen.value = Paint()
    }
}