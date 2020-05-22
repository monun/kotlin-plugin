import kotlin.NullPointerException

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = properties["pluginName"]?: throw NullPointerException("Group cannot be null")
version = properties["pluginVersion"]?: throw NullPointerException("Version cannot be null")

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("com.destroystokyo.paper:paper-api:1.13.2-R0.1-SNAPSHOT")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
	
    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
        }
    }
	
    shadowJar {
        archiveClassifier.set("lib")
    }
	
    create<Copy>("distJar") {
        from(jar)
        into("W:\\Servers\\parkour-maker\\plugins")
    }
}