import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.github.nwillc.vplugin") version "3.0.5"
}

group = "com.github.nwillc"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.6")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0")
    implementation("org.apache.kafka:kafka-clients:2.5.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
    testImplementation("org.assertj:assertj-core:3.16.1")
    testImplementation("org.testcontainers:testcontainers:1.14.1")
    testImplementation("org.testcontainers:kafka:1.14.1")

    testRuntimeOnly("org.slf4j:slf4j-simple:1.7.30")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    withType<Test> {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
            events("passed", "failed", "skipped")
        }
    }
}
