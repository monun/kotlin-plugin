plugins {
    kotlin("jvm") version "1.4.31"
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlinx("kotlinx-serialization-json", "1.1.0"))
    implementation(kotlinx("kotlinx-coroutines-core", "1.4.2"))
}

fun kotlinx(module: String, version: String): String {
    return "org.jetbrains.kotlinx:$module:$version"
}