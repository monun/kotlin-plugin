plugins {
    java
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

subprojects {
    apply(plugin = "java")
    if (name == "entrypoint") return@subprojects

    apply(plugin = "com.github.johnrengelman.shadow")

    version = name.substring(1) // v 접두사 제거

    repositories {
        mavenCentral()
        maven(url = "https://papermc.io/repo/repository/maven-public/")
    }

    dependencies {
        implementation(project(":entrypoint"))
    }

    tasks {
        withType(JavaCompile::class) {
            sourceCompatibility = "11"
            targetCompatibility = "11"
        }
        processResources {
            filesMatching("**/*.yml") {
                expand(project.properties)
            }
        }
        shadowJar {
            archiveBaseName.set("Kotlin")
            // Remove 'all' classifier
            archiveClassifier.set("")
            append("plugin.yml")
        }
        create<Copy>("upstream") {
            from(shadowJar)
            into(File(rootProject.buildDir, "libs"))
        }
        build {
            dependsOn(named("upstream"))
        }
    }

    sourceSets {
        main {
            resources {
                srcDirs(File(rootDir, "common"))
            }
        }
    }
}

tasks {
    all {
        onlyIf { it == clean.get() }
    }
}