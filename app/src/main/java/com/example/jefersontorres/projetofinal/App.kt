package com.example.jefersontorres.projetofinal

import android.app.Application
import android.util.Log
import android.content.res.Configuration


/**
 * Created by Jeferson Torres on 07/11/2017.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        IMCDataHelperSingleton.helper = IMCDataHelper(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.e("TRACKING APP", "TRIM MEMORY NOTIFICATION")
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Log.e("TRACKING APP", "CONFIGURATION CHANGE")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.e("TRACKING APP", "LOW MEMORY NOTIFICATION")
    }

    override fun onTerminate() {
        super.onTerminate()
        Log.e("TRACKING APP", "TERMINATE")

    }
}