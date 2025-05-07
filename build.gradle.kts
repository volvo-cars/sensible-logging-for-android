plugins {
    alias(libs.plugins.android.library) apply false
    kotlin("android") version libs.versions.kotlin apply false
    alias(libs.plugins.maven.publish) apply false
}