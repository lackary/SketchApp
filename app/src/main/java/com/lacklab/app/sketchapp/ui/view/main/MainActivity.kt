package com.lacklab.app.sketchapp.ui.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lacklab.app.sketchapp.R
import com.lacklab.app.sketchapp.base.BaseActivity
import com.lacklab.app.sketchapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(){

    private val mainViewModel: MainViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getVM() = mainViewModel

    override fun bindVM(binding: ActivityMainBinding, viewModel: MainViewModel) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}