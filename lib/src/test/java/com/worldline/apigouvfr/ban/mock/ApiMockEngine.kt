package com.worldline.apigouvfr.ban.mock

import com.worldline.apigouvfr.ban.validators
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class ApiMockEngine {

    internal inline operator fun <reified T> invoke(
        httpStatusCode: HttpStatusCode,
        response: T?
    ): HttpClient =
        HttpClient(MockEngine) {
            engine {
                addHandler {
                    add(httpStatusCode, response)
                }
            }
            contentNegociators()
            validators()
        }

    internal fun error(): HttpClient = HttpClient {
        contentNegociators()
        validators()
    }

    inline fun <reified T> MockRequestHandleScope.add(
        httpStatusCode: HttpStatusCode,
        response: T?
    ): HttpResponseData {
        return when (httpStatusCode) {
            HttpStatusCode.OK -> {
                respond(Json.encodeToString(response), httpStatusCode, mockResponseHeaders)
            }
            else -> {
                respond(httpStatusCode.toString(), httpStatusCode, mockResponseHeaders)
            }
        }
    }
}

internal val mockResponseHeaders =
    headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))

internal fun HttpClientConfig<*>.contentNegociators() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }
}