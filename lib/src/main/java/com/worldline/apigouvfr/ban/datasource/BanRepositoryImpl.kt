package com.worldline.apigouvfr.ban.datasource

import com.worldline.apigouvfr.ban.BanResult
import com.worldline.apigouvfr.ban.datasource.response.BanFeatureCollectionResponse
import com.worldline.apigouvfr.ban.exception.safeApiCall
import com.worldline.apigouvfr.ban.parameter.BanReverseParameters
import com.worldline.apigouvfr.ban.parameter.BanSearchParameters
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineDispatcher

internal class BanRepositoryImpl(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val client: HttpClient
) : BanRepository {

    override suspend fun search(banSearchParameters: BanSearchParameters): BanResult<BanFeatureCollectionResponse> =
        safeApiCall(coroutineDispatcher) {
            val content = client
                .get(urlString = "search/") {
                    parameter("q", banSearchParameters.query)
                    parameter("limit", banSearchParameters.limit)
                    parameter("autocomplete", banSearchParameters.autocomplete)
                    parameter("type", banSearchParameters.type?.queryValue)
                    parameter("lat", banSearchParameters.latitude)
                    parameter("long", banSearchParameters.longitude)
                }
                .body<BanFeatureCollectionResponse>()
            content
        }

    override suspend fun searchCsv(): BanResult<Any> = with(coroutineDispatcher) {
        // TODO Not yet implemented
        BanResult.ClientError()
    }

    override suspend fun reverse(banReverseParameters: BanReverseParameters): BanResult<BanFeatureCollectionResponse> =
        safeApiCall(coroutineDispatcher) {
            val content = client
                .get(urlString = "reverse/") {
                    parameter("lat", banReverseParameters.latitude)
                    parameter("long", banReverseParameters.longitude)
                    parameter("type", banReverseParameters.type?.queryValue)
                }
                .body<BanFeatureCollectionResponse>()
            content
        }

    override suspend fun reverseCsv(): BanResult<Any> = with(coroutineDispatcher) {
        // TODO Not yet implemented
        BanResult.ClientError()
    }
}
