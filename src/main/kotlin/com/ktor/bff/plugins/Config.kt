package com.ktor.bff.plugins

import com.google.gson.Gson
import com.zinc.money.bff.commons.ServerConfig
import com.ktor.bff.commons.ServerConstants
import io.ktor.server.application.*
import org.yaml.snakeyaml.Yaml

fun Application.loadServerConfig() {
    val yaml = Yaml()
    val inputStream = this::class.java.classLoader.getResourceAsStream("application.yaml")
    val config = yaml.load<Map<String, Any>>(inputStream)

    // Get the current environment
    val environment = (config["ktor"] as Map<String, Any>)["deployment"]?.let {
        (it as Map<String, Any>)["environment"] as String
    } ?: "dev"

    // Access environment-specific configurations
    val envConfig = (config["ktor"] as Map<String, Any>)["environment"]?.let {
        (it as Map<String, Any>)[environment] as Map<String, String>
    }
    val gson = Gson()
    print(gson.toJson(envConfig))
    com.ktor.bff.commons.ServerConstants.serverConfig = gson.fromJson(gson.toJson(envConfig), ServerConfig::class.java)
}