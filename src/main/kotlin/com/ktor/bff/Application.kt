package com.ktor.bff

import com.zinc.money.bff.plugins.*
import configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
/*    embeddedServer(Netty,
        port = 9432,
        host = "0.0.0.0",
        module = Application::module
        ).start(wait = true)*/
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    loadServerConfig()
    configureSerialization()
    configureHTTP()
    configureSecurity()
    configureRouting()
}
