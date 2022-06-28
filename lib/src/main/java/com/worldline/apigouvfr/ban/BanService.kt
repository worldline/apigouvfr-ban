package com.worldline.apigouvfr.ban

import com.worldline.apigouvfr.ban.datasource.BanRepository
import com.worldline.apigouvfr.ban.datasource.BanRepositoryImpl
import com.worldline.apigouvfr.ban.exception.BanException
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json

/**
 * Main entry point to the library
 */
public class BanService private constructor(
    banRepositoryImpl: BanRepository
) : BanRepository by banRepositoryImpl {

    public companion object {
        public operator fun invoke(): BanService {
            return BanService(
                BanRepositoryImpl(
                    Dispatchers.IO,
                    getHttpClient()
                )
            )
        }

        private fun getHttpClient() = HttpClient(CIO) {
            defaultRequest {
                url.host = "api-adresse.data.gouv.fr"
                url.protocol = URLProtocol.HTTPS
            }
            contentNegociators()
            validators()
        }
    }
}

internal fun HttpClientConfig<CIOEngineConfig>.contentNegociators() {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
        engine {
            requestTimeout = 60000
        }
    }
}

internal fun HttpClientConfig<*>.validators() {
    HttpResponseValidator {
        handleResponseExceptionWithRequest { cause, request ->
            if (request.call.response.status.value !in 200..299) {
                throw BanException(request.call.response.status.value)
            }
            if (cause !is ClientRequestException) {
                return@handleResponseExceptionWithRequest
            }
            throw BanException(cause.response.status.value)
        }
    }
}
