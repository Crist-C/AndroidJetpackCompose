// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.2" apply false
    id("com.android.library") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false


    //Hilt
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

ext {
    var activityVersion = "1.1.0"
    var appCompatVersion = "1.2.0"
    var constraintLayoutVersion = "2.0.2"
    var coreTestingVersion = "2.1.0"
    var coroutines = "1.3.9"
    var lifecycleVersion = "2.2.0"
    var materialVersion = "1.2.1"
    var roomVersion = "2.2.5"
    // testing
    var junitVersion = "4.13.1"
    var espressoVersion = "3.1.0"
    var androidxJunitVersion = "1.1.2"
}