pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        gradlePluginPortal()
    }
    plugins {
        id("dagger.hilt.android.plugin") version "2.44"
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // Jitpack 저장소 추가
    }
}

rootProject.name = "project"
include(":app")
