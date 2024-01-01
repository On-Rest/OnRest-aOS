pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {url = uri("https://jitpack.io") }
    }
}

buildscript {
    repositories{
        google()
        mavenCentral()
    }
}

rootProject.name = "onRest"
include(":app")