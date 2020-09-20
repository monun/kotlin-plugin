package com.github.noonmaru.kotlin

import com.github.noonmaru.kommand.kommand
import com.github.noonmaru.kotlin.util.GitHubSupport
import com.github.noonmaru.kotlin.util.UpToDateException
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * @author Noonmaru
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
                executes {
                    val sender = it.sender
                    sender.sendMessage("Attempt to update.")
                    update() {
                        onSuccess { url ->
                            sender.sendMessage("Updated successfully. Applies after the server restarts.")
                            sender.sendMessage(url)
                        }
                        onFailure { t ->
                            if (t is UpToDateException) sender.sendMessage("Up to date!")
                            else {
                                sender.sendMessage("Update failed. Check the console.")
                                t.printStackTrace()
                            }
                        }
                    }
                }
            }
        }
    }

    fun update(callback: (Result<String>.() -> Unit)? = null) {
        GlobalScope.launch {
            val file = file
            val updateFile = File(file.parentFile, "update/${file.name}")
            GitHubSupport.downloadUpdate(updateFile, "noonmaru", "kotlin-plugin", description.version, callback)
        }
    }
}