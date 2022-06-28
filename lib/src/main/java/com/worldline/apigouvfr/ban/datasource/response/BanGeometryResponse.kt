package com.worldline.apigouvfr.ban.datasource.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BanGeometryResponse(
    @SerialName("coordinates")
    val coordinates: List<Double>,
    @SerialName("type")
    val type: String
)
