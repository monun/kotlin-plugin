plugins {
    kotlin("jvm") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

repositories {
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://jitpack.io/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("com.github.noonmaru:kommand:0.3.1")

    compileOnly("org.spigotmc:spigot-api:1.8-R0.1-SNAPSHOT")
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
        archiveBaseName.set(project.property("pluginName").toString())
        archiveVersion.set("") // For bukkit plugin update
        archiveClassifier.set("") // Remove 'all'
        relocate("com.github.noonmaru.kommand", "${rootProject.group}.${rootProject.name}.kommand")
    }
    create<Copy>("copyJarToDocker") {
        from(shadowJar)

        var dest = File(".docker/plugins")
        // Copy bukkit plugin update folder
        if (File(dest, shadowJar.get().archiveFileName.get()).exists()) dest = File(dest, "update")

        into(dest)

        doLast {
            println("Copy to ${dest.path}")
        }
    }
}