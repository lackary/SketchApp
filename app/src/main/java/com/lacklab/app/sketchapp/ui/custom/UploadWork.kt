package com.lacklab.app.sketchapp.ui.custom

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWork(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        
        return Result.success()
    }
}