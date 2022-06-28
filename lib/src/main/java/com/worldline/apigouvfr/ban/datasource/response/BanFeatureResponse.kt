package com.worldline.apigouvfr.ban.datasource.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BanFeatureResponse(
    @SerialName("type")
    val type: String? = null,
    @SerialName("geometry")
    val geometry: BanGeometryResponse,
    @SerialName("properties")
    val properties: BanPropertiesResponse
)
