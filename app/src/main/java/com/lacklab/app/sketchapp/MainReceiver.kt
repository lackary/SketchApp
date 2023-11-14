package com.lacklab.app.sketchapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class MainReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.d("onReceiver")
    }
}