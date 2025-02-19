package com.ktor.bff.plugins

import cardRoutes
import countryRoutes
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<ApiException> { call, cause ->
            call.respond(status = cause.statusCode, message = mapOf("message" to cause.message))
        }
    }
    routing {
        route("/api/v1/bff"){
            openAPI(path = "openapi")
            get("/") {
                call.respondText("<H2>Server running! The BFF layer is Active</H2>", ContentType.parse("text/html"))
            }
            countryRoutes()
        }
    }
}
