package com.mazecube.myapplication

import android.app.Application
import com.mazecube.myapplication.utill.CustomDebugTree
import com.orhanobut.logger.AndroidLogAdapter
import com.mazecube.myapplication.BuildConfig
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
        if(BuildConfig.DEBUG) Timber.plant(CustomDebugTree())

        Timber.e("Application On Create")
    }
}