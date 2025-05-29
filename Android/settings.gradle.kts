pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://storage.googleapis.com/download.flutter.io")
    }
}

rootProject.name = "Device Info Viewer"
include(":app")
val filePath = settingsDir.parentFile.toString() + "/device_details_flutter_module/.android/include_flutter.groovy"
if (File(filePath).exists()) {
    apply(from = filePath)
} else {
    throw GradleException("Flutter module not found at $filePath")
}