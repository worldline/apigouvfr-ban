package com.worldline.apigouvfr.ban.datasource.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class BanPropertiesResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("type")
    val type: String,
    @SerialName("score")
    val score: Double? = null,
    @SerialName("importance")
    val importance: Double? = null,
    @SerialName("x")
    val x: Double? = null,
    @SerialName("y")
    val y: Double? = null,
    @SerialName("label")
    val label: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("housenumber")
    val housenumber: String? = null,
    @SerialName("street")
    val street: String? = null,
    @SerialName("locality")
    val locality: String? = null,
    @SerialName("postcode")
    val postcode: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("citycode")
    val citycode: String? = null,
    @SerialName("context")
    val context: String? = null,
    @SerialName("district")
    val district: String? = null,
    @SerialName("oldcity")
    val oldcity: String? = null,
    @SerialName("oldcitycode")
    val oldcitycode: String? = null
)
