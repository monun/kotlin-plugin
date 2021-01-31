package com.github.monun.kotlin

import com.github.noonmaru.kommand.kommand
import com.github.monun.kotlin.util.GitHubSupport
import com.github.monun.kotlin.util.UpToDateException
import com.github.monun.kotlin.util.updateFromGitHubMagically
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

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