plugins {
    id("com.android.library")
    id("com.vanniktech.maven.publish")
}

mavenPublishing {
    coordinates(artifactId = "sensible-logging-lifecycle")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "sh.vcm.sensiblelogging.lifecycle"

    defaultConfig {
        minSdk = 21
        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
    implementation(project(":android"))
    implementation(libs.kotlin.stdlib)
    implementation(libs.x.appcompat)
    implementation(libs.x.lifecycle.process)
    implementation(libs.x.lifecycle.service)
    implementation(libs.x.lifecycle.common.java8)
    testImplementation(libs.mockk)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}