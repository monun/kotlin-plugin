plugins {
    kotlin("jvm") version "1.4.0"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlinx("kotlinx-serialization-core", "1.0.0-RC"))
    implementation(kotlinx("kotlinx-coroutines-core", "1.3.9"))
}

fun kotlinx(module: String, version: String): String {
    return "org.jetbrains.kotlinx:$module:$version"
}