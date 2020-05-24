package com.github.noonmaru.kotlin

import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Noonmaru
 */
class KotlinPlugin : JavaPlugin() {
    override fun onEnable() {
        logger.info("Kotlin library loaded")
    }
}