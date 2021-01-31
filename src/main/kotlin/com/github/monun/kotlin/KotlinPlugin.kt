package com.github.monun.kotlin

import com.github.monun.kommand.kommand
import com.github.monun.kotlin.util.updateFromGitHubMagically
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Monun
 */
class KotlinPlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("Kotlin library loaded")

        setupCommands()
    }

    private fun setupCommands() = kommand {
        register("kotlin") {
            then("version") {
                executes { it.sender.sendMessage("Kotlin ${description.version}") }
            }
            then("update") {
                executes { ctx ->
                    val sender = ctx.sender
                    updateFromGitHubMagically("monun", "kotlin-plugin", "Kotlin.jar") { message ->
                        sender.sendMessage(message)
                    }
                }
            }
        }
    }
}