plugins {
    kotlin("jvm") version "1.3.72"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlinx("kotlinx-serialization-runtime", "0.20.0"))
    implementation(kotlinx("kotlinx-coroutines-core", "1.3.8"))
    val exposedVersion = "0.24.1"
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
}

fun kotlinx(module: String, version: String): String {
    return "org.jetbrains.kotlinx:$module:$version"
}