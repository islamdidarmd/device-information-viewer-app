package com.islamdidarmd.addtoappexample

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class FlutterEmbeddingActivity : FlutterActivity() {
    private val CHANNEL = "com.example.device_details_viewer/device_info"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "getDeviceInfo") {
                try {
                    val deviceInfo = hashMapOf(
                        "manufacturer" to android.os.Build.MANUFACTURER,
                        "model" to android.os.Build.MODEL,
                        "osVersion" to android.os.Build.VERSION.RELEASE,
                        "sdkVersion" to android.os.Build.VERSION.SDK_INT.toString()
                    )
                    result.success(deviceInfo)
                } catch (e: Exception) {
                    result.error("NATIVE_ERROR", e.localizedMessage, null)
                }
            } else {
                result.notImplemented()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
