package com.worldline.apigouvfr.ban.datasource

import com.worldline.apigouvfr.ban.BanResult
import com.worldline.apigouvfr.ban.datasource.response.BanFeatureCollectionResponse
import com.worldline.apigouvfr.ban.parameter.BanReverseParameters
import com.worldline.apigouvfr.ban.parameter.BanSearchParameters

internal interface BanRepository {

    /**
     * Geocoding entry point
     */
    suspend fun search(banSearchParameters: BanSearchParameters): BanResult<BanFeatureCollectionResponse>

    /**
     * Entry point for mass geocoding from a CSV file
     */
    suspend fun searchCsv(): BanResult<Any>

    /**
     * Reverse geocoding entry point
     */
    suspend fun reverse(banReverseParameters: BanReverseParameters): BanResult<BanFeatureCollectionResponse>

    /**
     * Entry point for reverse mass geocoding from a CSV file
     */
    suspend fun reverseCsv(): BanResult<Any>
}
