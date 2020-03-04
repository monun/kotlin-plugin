plugins {
    kotlin("jvm") version "1.3.70"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = properties["pluginName"]!!
version = properties["pluginVersion"]!!

repositories {
    mavenCentral()
    maven(url = "https://papermc.io/repo/repository/maven-public/") //paper
    maven(url = "https://jitpack.io/") //tap, psychic
}

dependencies {
    compile(kotlin("stdlib-jdk8")) //kotlin
    compileOnly("com.destroystokyo.paper:paper-api:1.13.2-R0.1-SNAPSHOT") //paper
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
    javadoc {
        options.encoding = "UTF-8"
    }
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
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
//    create<Copy>("distJar") {
//        from(jar)
//        into("W:\\Servers\\distServer\\plugins")
//    }
}