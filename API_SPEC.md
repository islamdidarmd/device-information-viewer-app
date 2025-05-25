# API Specification: Device Information MethodChannel

## 1. Channel Name

*   **Name:** `com.example.device_details_viewer/device_info`
    *   *Rationale: A reverse domain name style ensures uniqueness. Suffix `/device_info` specifies the domain of methods.*

## 2. Methods

*   **Method: `getDeviceInfo`**
    *   **Direction:** Flutter (running in `FlutterView`) -> Native (Android Host App, Kotlin)
    *   **Description:** Requests basic device hardware and OS information from the native platform.
    *   **Arguments (from Flutter to Native):** None.
    *   **Return (from Native to Flutter):**
        *   **Type:** `Map<String, String>` (or `Map<dynamic, dynamic>` on Flutter side before casting)
        *   **On Success:** A map containing the device details.
            *   **Key:** `"manufacturer"`
                *   **Value Type:** `String`
                *   **Description:** The manufacturer of the device (e.g., "Google", "Samsung").
                *   **Source:** `android.os.Build.MANUFACTURER`
            *   **Key:** `"model"`
                *   **Value Type:** `String`
                *   **Description:** The model name of the device (e.g., "Pixel 7", "Galaxy S23").
                *   **Source:** `android.os.Build.MODEL`
            *   **Key:** `"osVersion"`
                *   **Value Type:** `String`
                *   **Description:** The user-visible Android OS version string (e.g., "13", "14").
                *   **Source:** `android.os.Build.VERSION.RELEASE`
            *   **Key:** `"sdkVersion"`
                *   **Value Type:** `String`
                *   **Description:** The Android SDK API level as a string (e.g., "33", "34").
                *   **Source:** `android.os.Build.VERSION.SDK_INT.toString()`
        *   **On Failure (Native side):**
            *   The `result.error(errorCode, errorMessage, errorDetails)` method is called.
            *   Flutter will receive a `PlatformException`.
            *   **Example Error Codes:** `"UNAVAILABLE"`, `"NATIVE_ERROR"`.
    *   **Example Successful Return Map (JSON representation for clarity):**
        ```json
        {
          "manufacturer": "Google",
          "model": "Pixel 7",
          "osVersion": "14",
          "sdkVersion": "34"
        }
        ```
