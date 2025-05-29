package com.example.device_details_flutter_plugin

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** DeviceDetailsFlutterPlugin */
class DeviceDetailsFlutterPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "com.example.device_details_viewer/device_info")
    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
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

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
