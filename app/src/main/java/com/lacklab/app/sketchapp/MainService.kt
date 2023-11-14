package com.lacklab.app.sketchapp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import timber.log.Timber
import java.util.*

class MainService : Service() {

    private val localBinder = LocalBinder()

    private val generator = Random()

    val randomNumber: Int
        get() = generator.nextInt(100)

    inner class LocalBinder: Binder() {
        fun getService() = this@MainService
    }

    override fun onBind(intent: Intent?): IBinder? {
        Timber.d("onBind")
        return localBinder
    }

    override fun onCreate() {
        Timber.d("onCreate")

//        val testThread = Thread(Runnable {
//            // while loop
//            var count = 0;
//            while (true) {
//                count++;
//                Timber.d("Thread:  $count - random: $randomNumber")
//                Thread.sleep(100)
//            }
//        })
//        testThread.start()
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }
}