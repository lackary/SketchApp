package com.lacklab.app.sketchapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope

abstract class BaseFragment<DB: ViewDataBinding, VM: BaseViewModel> : Fragment() {

    private lateinit var _binding: DB
    val binding: DB
        get() = _binding
    private lateinit var _viewModel: VM
    val viewModel: VM
        get() = _viewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel = getVM()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindVM(binding, viewModel)
    }

    @get:LayoutRes
    abstract val layoutId: Int

    abstract fun getVM(): VM

    abstract fun bindVM(binding: DB, viewModel: VM)

    fun showToastMessage(message: String?) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            execute()
        }
    }
}