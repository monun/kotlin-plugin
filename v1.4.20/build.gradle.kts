plugins {
    kotlin("jvm") version "1.4.20"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlinx("kotlinx-serialization-json", "1.0.1"))
    implementation(kotlinx("kotlinx-coroutines-core", "1.4.1"))
    val exposedVersion = "0.28.1"
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
}

fun kotlinx(module: String, version: String): String {
    return "org.jetbrains.kotlinx:$module:$version"
}