package com.lacklab.app.sketchapp.ui.view.main

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.lacklab.app.sketchapp.MainService
import com.lacklab.app.sketchapp.R
import com.lacklab.app.sketchapp.base.BaseActivity
import com.lacklab.app.sketchapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mainService: MainService
    private var bound = false

    private lateinit var button: Button

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Timber.d("onServiceConnected")
            val binder = service as MainService.LocalBinder
            mainService = binder.getService()
            println("random: ${mainService.randomNumber}")
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Timber.d("onServiceDisconnected")
            bound = false
        }

    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getVM() = mainViewModel

    override fun bindVM(binding: ActivityMainBinding, viewModel: MainViewModel) {
        Timber.d("bindVM")
        button = binding.buttonRandom
        button.setOnClickListener{
            Timber.d("onClick")
            if(bound) {
                val number = mainService.randomNumber
                Toast.makeText(this, "number: $number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Intent(this, MainService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE )
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        bound = false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Timber.d("onTouchEvent")
        return super.onTouchEvent(event)
    }
}