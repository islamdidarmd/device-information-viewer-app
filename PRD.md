# Product Requirements Document (PRD): Device Details Viewer

## 1. Introduction

The "Device Details Viewer" is a mobile application designed to demonstrate the "Flutter Add-to-App" capability. It showcases how a native Android application (written in Kotlin) can launch and embed a Flutter view. The application starts with a native Android screen containing a button. Clicking this button will launch another native Android screen that hosts a Flutter UI. This Flutter UI will then use Method Channels to request fundamental device details (manufacturer, model, Android OS version, and SDK version) from the native Android platform and display them.

This project serves as a clear, concise, and effective example for tutorial series demonstrating:
*   Embedding a Flutter view within a native Android application.
*   Facilitating communication (MethodChannel) between the embedded Flutter UI and the native Android host.
*   Integrating a Flutter module as a source dependency (not as an AAR) into a host native Android application.

## 2. Goals & Objectives

*   **Primary Goal:** To serve as a clear, concise, and effective example in a tutorial series demonstrating how to embed a Flutter view within a native Android application (using Kotlin) and facilitate communication between them.
*   **Educational Objective:** Enable tutorial followers to understand and implement the integration of Flutter modules as distinct views/screens within an existing native Android app structure.
*   **Technical Objective:** Showcase launching a Flutter view from native Android, and then Flutter requesting read-only data from the native Android environment and displaying it.
*   **Simplicity:** Keep the codebase for the Flutter module and the specific native data-fetching logic minimal and easy to understand.
*   **Foundation:** Provide an example where the Flutter part is integrated as a source dependency.

## 3. Target Audience (for the Tutorial)

*   Native Android developers looking to integrate Flutter views/screens into their existing applications.
*   Flutter developers interested in how their modules are consumed by native Android apps.
*   Developers new to Flutter-Native communication in an add-to-app context.

## 4. Functional Requirements

*   **FR1: Native Android Application Flow**
    *   **FR1.1:** The application must have an initial native Android screen (e.g., an Activity, written in Kotlin).
    *   **FR1.2:** This initial native screen must contain a button labeled "View Device Details in Flutter" (or similar).
    *   **FR1.3:** Clicking this button must launch a new native Android screen (e.g., another Activity).
    *   **FR1.4:** This second native screen must be responsible for hosting and displaying a Flutter view (`FlutterView` / `FlutterActivity` / `FlutterFragment`).

*   **FR2: Embedded Flutter User Interface**
    *   **FR2.1:** The Flutter UI, once loaded into the native host screen, must display a simple interface.
    *   **FR2.2:** This Flutter screen must contain a button labeled "Get Device Details".
    *   **FR2.3:** The Flutter screen must have designated areas (e.g., Text widgets) to display the following information, initially showing placeholder text or being empty:
        *   Manufacturer
        *   Model
        *   Android Version
        *   SDK Version
    *   **FR2.4:** Labels for the displayed information should be clear (e.g., "Manufacturer: [value]").

*   **FR3: Native Android Device Information Retrieval (Core Logic)**
    *   **FR3.1:** The native Android code (accessible via MethodChannel) must be able to retrieve the device manufacturer (using `android.os.Build.MANUFACTURER`).
    *   **FR3.2:** The native Android code must be able to retrieve the device model (using `android.os.Build.MODEL`).
    *   **FR3.3:** The native Android code must be able to retrieve the Android OS version (using `android.os.Build.VERSION.RELEASE`).
    *   **FR3.4:** The native Android code must be able to retrieve the Android SDK API level (using `android.os.Build.VERSION.SDK_INT`).

*   **FR4: Flutter-Native Communication (MethodChannel)**
    *   **FR4.1:** Pressing the "Get Device Details" button in the embedded Flutter UI must trigger a `MethodChannel` call to the native Android side.
    *   **FR4.2:** The native Android side (associated with the Flutter engine hosting the view) must listen for and handle this specific `MethodChannel` call.
    *   **FR4.3:** Upon receiving the call, the native Android side will collect the device details specified in FR3.
    *   **FR4.4:** The native Android side must package these details into a `Map` and send it back to Flutter via the `MethodChannel`'s `Result` callback.
    *   **FR4.5:** The Flutter module must receive this `Map`, parse it, and update the corresponding Text widgets on its screen to display the retrieved information.

## 5. Non-Functional Requirements

*   **NFR1: Simplicity & Clarity:** The implementation of the Flutter module and the native data-fetching logic should be straightforward. The native Android code for hosting Flutter should follow common practices.
*   **NFR2: Read-Only Data:** The app will only fetch and display existing device data.
*   **NFR3: No Special Permissions:** Retrieval of the specified basic device information does not require special Android runtime permissions.
*   **NFR4: Clear Data Flow:** The architecture must clearly demonstrate:
    *   Native launching Flutter.
    *   Flutter (in `FlutterView`) requesting data from native.
    *   Native responding to Flutter.
*   **NFR5: Minimal Native Code (for data fetching):** The native Android code for *getting device details* should be concise. Hosting Flutter will involve standard Android boilerplate.
*   **NFR6: Source Dependency:** The Flutter module itself should be structured to be integrated as a source dependency (not as an AAR).
*   **NFR7: Performance:** Communication should be reasonably efficient.

## 6. Success Metrics (for the Tutorial App)

*   Successful launch of the Flutter view from the native Android screen.
*   Successful retrieval and display of device information within the Flutter view.
*   Clarity of the code in demonstrating native-to-Flutter screen transition and subsequent Flutter-to-native data fetching.
*   Positive feedback from tutorial users.

## 7. Future Considerations (Out of Scope for Initial Version)

*   Passing initial data from Native to Flutter when the Flutter view is launched.
*   More complex Flutter UI within the native screen.
*   Bi-directional communication beyond a single request-response.
*   Automated testing.