package com.ktor.bff.plugins

import com.google.gson.FieldNamingPolicy
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.gson.*


object ApiClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson {
                setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                setPrettyPrinting()
            }
        }
        install(io.ktor.client.plugins.logging.Logging) {
            level = io.ktor.client.plugins.logging.LogLevel.BODY
        }
    }

    suspend inline fun <reified T> makeRequest(
        requestUrl: String,
        requestType: HttpMethod,
        headers: Map<String, String>?,
        pathParams: Map<String, Any>? = null,
        queryParams: Map<String, Any>? = null
    ): T {
        var modifiedUrl = requestUrl

        pathParams?.forEach { (key, value) ->
            modifiedUrl = modifiedUrl.replace("{$key}", value.toString())
        }
        val httpRequestBuilder: HttpRequestBuilder = HttpRequestBuilder().apply {
            url(modifiedUrl)
            method = requestType
            queryParams?.forEach { (key, value) ->
                parameter(key, value)
            }
            headers?.forEach { e ->
                header(e.key, e.value)
            }

        }
        httpRequestBuilder.method = requestType
        try {
            val response = client.request(httpRequestBuilder)
            if (response.status.isSuccess()) {
                return response.body()
            } else {
                throw handleError(response)
            }
        } catch (e: Exception) {
            print(e.message)
            throw e
        }
    }

    fun handleError(response: HttpResponse): Throwable {
        when (response.status) {
            HttpStatusCode.NotFound -> throw ApiException.NotFoundException()
            HttpStatusCode.Unauthorized -> throw ApiException.UnauthorizedException()
            HttpStatusCode.Forbidden -> throw ApiException.ForbiddenException()
            HttpStatusCode.InternalServerError -> throw ApiException.InternalServerException()
            HttpStatusCode.BadGateway -> throw ApiException.BadGatewayException()
            HttpStatusCode.BadRequest -> throw ApiException.BadRequestException()
            else -> throw ApiException.UnknownException("Unexpected status code: ${response.status}")

        }
    }

}

sealed class ApiException(val statusCode: HttpStatusCode, message: String) : RuntimeException(message) {
    class NotFoundException(message: String = "Resource not found") : ApiException(HttpStatusCode.NotFound, message)
    class UnauthorizedException(message: String = "Unauthorized access") :
        ApiException(HttpStatusCode.Unauthorized, message)

    class ForbiddenException(message: String = "Forbidden access") : ApiException(HttpStatusCode.Forbidden, message)
    class InternalServerException(message: String = "Internal server error") :
        ApiException(HttpStatusCode.InternalServerError, message)

    class BadRequestException(message: String = "Bad request") : ApiException(HttpStatusCode.BadRequest, message)
    class BadGatewayException(message: String = "Bad gateway") : ApiException(HttpStatusCode.BadGateway, message)
    class UnknownException(message: String = "An unknown error occurred") :
        ApiException(HttpStatusCode.InternalServerError, message)
}
