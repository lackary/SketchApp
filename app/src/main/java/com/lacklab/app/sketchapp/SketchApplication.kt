package com.lacklab.app.sketchapp

import android.app.Application
import android.content.Context
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class SketchApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.d("attachBaseContext")
        super.attachBaseContext(base)
    }
    override fun onCreate() {
        Timber.d("onCreate")
        super.onCreate()

    }
}