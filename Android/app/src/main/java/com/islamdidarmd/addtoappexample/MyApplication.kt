package com.islamdidarmd.addtoappexample

import android.app.Application

class MyApplication : Application() {
    //lateinit var flutterEngine: FlutterEngine
    override fun onCreate() {
        super.onCreate()

        /*flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor
            .executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())

        FlutterEngineCache
            .getInstance()
            .put("engine_0", flutterEngine)
*/
    }
}