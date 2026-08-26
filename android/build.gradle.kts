plugins {
    id("com.android.library")
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    coordinates(artifactId = "sensible-logging-android")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "sh.vcm.sensiblelogging.android"

    defaultConfig {
        minSdk = 16
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }

    lint {
        disable.add("GradleDependency")
        disable.add("AndroidGradlePluginVersion")
        quiet = false
        abortOnError = true
        warningsAsErrors = true
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    api(project(":core"))
    implementation(libs.kotlin.stdlib)
    testImplementation(libs.mockk)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}
