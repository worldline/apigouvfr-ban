package com.worldline.apigouvfr.ban.datasource.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BanFeatureCollectionResponse(
    @SerialName("type")
    val type: String? = null,
    @SerialName("version")
    val version: String? = null,
    @SerialName("features")
    val features: List<BanFeatureResponse> = emptyList(),
    @SerialName("licence")
    val licence: String? = null,
    @SerialName("attribution")
    val attribution: String? = null,
    @SerialName("query")
    val query: String? = null,
    @SerialName("limit")
    val limit: Int? = null
)
