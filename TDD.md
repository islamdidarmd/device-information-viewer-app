# Technical Design Document (TDD): Device Details Viewer

## 1. Overview

This document outlines the technical architecture for the "Device Details Viewer." The system comprises a **Native Android Host Application** (written in Kotlin) and a **Flutter Module**. The Native Android app initiates a screen that embeds a view powered by the Flutter Module. This Flutter view contains a UI (the "Flutter App" screen) that uses a `MethodChannel` to request device information from the native Android platform, which is then displayed within the Flutter UI.

## 2. System Architecture

```
+-----------------------------+      +---------------------------------+      +------------------------------------+
| Native Android Host App     | ---> | Native Android Screen           | ---> | Flutter Module (Embedded via       |
| (e.g., MyNativeHostApp)     |      | (e.g., FlutterEmbeddingActivity)|      | source dependency)                 |
|                             |      | - Hosts FlutterView/Fragment    |      | - FlutterView / FlutterActivity    |
| - Kotlin                    |      | - Button "View Details"         |      | - MethodChannel Call Logic         |
+-----------------------------+      +---------------------------------+      +------------------------------------+
                                                                                      |
                                                                                      v
                                                                            +-------------------------------+
                                                                            | Flutter UI Screen ("App")     |
                                                                            | (e.g., DeviceDetailsScreen)   |
                                                                            | - Button "Get Device Details" |
                                                                            | - Text Widgets                |
                                                                            +-------------------------------+
```

## 3. Native Android Host Application Design (`MyNativeHostApp`)

* **Module Structure:** A standard Android application project (in Kotlin). This project will depend on the Flutter Module as a source dependency.
* **Screen 1 (e.g., `MainActivity.kt`)**
    * **UI:** A simple native Android layout (`activity_main.xml`) with a `Button` ("View Device Details in Flutter").
    * **Logic:** On button click, launches `FlutterEmbeddingActivity`.
* **Screen 2 (e.g., `FlutterEmbeddingActivity.kt`)**
    * **Purpose:** To host the UI provided by the Flutter Module.
    * **Implementation:** Host a `FlutterView`.
    * **MethodChannel Handler Setup:**
        * Registered within `FlutterEmbeddingActivity`.
        * Handles the `"getDeviceInfo"` method call.
        * Retrieves device information using `android.os.Build` constants.
        * Sends data back to Flutter as a `HashMap<String, String>`.

## 4. Flutter Module Design (`device_details_flutter_module`)

* **Module Overview:**
    * A Flutter project created using `flutter create --template module device_details_flutter_module`.
    * Designed to be included as a source dependency.
* **Flutter UI Screen (e.g., `lib/device_details_screen.dart`)**
    * **Widget Structure:** A `StatefulWidget` (e.g., `DeviceDetailsScreen`).
    * **UI Components:** `Scaffold`, `AppBar`, `Column`, `Text` widgets, `ElevatedButton`.
    * **State Management:** Uses `StatefulWidget` and `setState` to manage and display fetched device information and error messages.
    * **MethodChannel Invocation:**
        * Uses a `MethodChannel` instance with the name `com.example.device_details_viewer/device_info`.
        * An `async` function `_getDeviceDetails` calls `platform.invokeMethod('getDeviceInfo')`.
        * Handles success (updates state with map data) and `PlatformException` (updates state with error message).
* **Module Entry Point (`lib/main.dart`)**
    * The `main()` function runs the `DeviceDetailsScreen` widget within a `MaterialApp`.
    * This serves as the primary UI exposed by the Flutter module.

## 5. Project Structure (Illustrative)

* **Monorepo Structure:**

  ```
  AddToAppExample/
  ├── android/                # Native Android host app (Kotlin)
  │   ├── app/
  │   │   ├── build.gradle
  │   │   └── src/main/
  │   │       ├── java/com/example/mynativehostapp/
  │   │       │   ├── MainActivity.kt
  │   │       │   └── FlutterEmbeddingActivity.kt
  │   │       ├── res/layout/activity_main.xml
  │   │       └── AndroidManifest.xml
  │   └── settings.gradle (includes the Flutter module)
  └── shared/
      ├── device_details_flutter_module/   # Flutter module (source dependency)
      │   ├── .android/
      │   ├── lib/
      │   │   ├── main.dart
      │   │   └── device_details_screen.dart
      │   └── pubspec.yaml
      └── flutter_app/                     # (Optional) Standalone Flutter app for development
  ```

## 6. Integration Documentation

* An integration guide will be provided in a separate Markdown file.

## 7. Minimum SDK

* The minimum Android SDK version is 26.
