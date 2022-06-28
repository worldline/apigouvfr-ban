package com.worldline.apigouvfr.ban.parameter

/**
 * Reverse search parameters
 *
 * @property latitude Latitude (in degrees). Example: "50.5674052"
 * @property longitude Longitude (in degrees). Example: "3.0287678"
 * @property type Type of expected result
 */
public class BanReverseParameters(
    public val latitude: String,
    public val longitude: String,
    public val type: Type? = null
)