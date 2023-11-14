package com.lacklab.app.sketchapp.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.lacklab.app.sketchapp.R
import com.lacklab.app.sketchapp.databinding.LayoutNumberSelectBinding

class NumberSelectLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val binding: LayoutNumberSelectBinding =
        LayoutNumberSelectBinding.inflate(LayoutInflater.from(context), this, true)
    private var minValue: Int = 0
    private var maxValue: Int = 0
    private var defaultValue: Int = 0

    var textValue: Int = 0

    private var listener: NumberSelectListener? = null
    interface NumberSelectListener {
        fun onValueChange(value: Int)
    }
    init {
//        View.inflate(context, R.layout.layout_number_select, this)
        descendantFocusability = ViewGroup.FOCUS_BLOCK_DESCENDANTS

        if (attrs != null) {
            val attributes = context.theme.obtainStyledAttributes(
                attrs, R.styleable.NumberSelect, 0, 0)
            maxValue = attributes.getInt(R.styleable.NumberSelect_max_value, Int.MAX_VALUE)
            minValue = attributes.getInt(R.styleable.NumberSelect_max_value, 0)
            defaultValue = attributes.getInt(R.styleable.NumberSelect_default_value, 0)
            textValue = this.defaultValue
            with(binding) {
                textViewValue.text = defaultValue.toString()
                buttonMax.setOnClickListener {
                    addTextValue()
                }
                buttonMin.setOnClickListener {
                    minusTextValue()
                }
            }
        }
    }


    private fun addTextValue() {
        if (textValue < maxValue) {
            textValue++
            with(binding) {
                textViewValue.text = textValue.toString()
            }
        }
    }

    private fun minusTextValue() {
        if (textValue < minValue) {
            textValue--
            with(binding) {
                textViewValue.text = textValue.toString()
            }
        }
    }

    fun setListener(listener: NumberSelectListener) {
        this.listener = listener
    }
}