package com.github.noonmaru.kotlin

import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Nemo
 */
class KotlinPlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("Kotlin library loaded")
    }
}