plugins {
    kotlin("jvm")
    id("com.vanniktech.maven.publish")
}

kotlin {
    jvmToolchain(17)
}

mavenPublishing {
    coordinates(artifactId = "sensible-logging-core")
}

dependencies {
    implementation(libs.kotlin.stdlib)
    testImplementation(libs.mockk)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
