
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

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
    testImplementation("org.assertj:assertj-core:3.16.1")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    withType<Test> {
          useJUnitPlatform()
          testLogging {
              showStandardStreams = true
              events("passed", "failed", "skipped")
          }
      }
}
