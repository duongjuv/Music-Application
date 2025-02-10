plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    // ==> Bổ sung:
    id("androidx.room") version "2.6.1" apply false
    //id("com.google.gms.google-services") version "4.4.1" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false
    // <== END
}

// ==> Bổ sung đoạn code sau:
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}
// <== END