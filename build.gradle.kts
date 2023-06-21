import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.noarg.gradle.NoArgExtension

plugins {
    kotlin("jvm") version "1.8.21"

    id("org.jetbrains.kotlin.plugin.noarg") version "1.8.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.21"

    application
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.Enumerated")
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Enumerated")
}

group = "org.hibernate.bugs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate.orm:hibernate-core:6.2.5.Final")
    implementation("org.hibernate.orm:hibernate-testing:6.2.5.Final")
    testRuntimeOnly("com.h2database:h2:2.1.214")

    testImplementation(kotlin("test"))
}

configure<NoArgExtension> {
    annotation("jakarta.persistence.Entity")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

tasks.withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
        jvmTarget = "17"
    }
}
