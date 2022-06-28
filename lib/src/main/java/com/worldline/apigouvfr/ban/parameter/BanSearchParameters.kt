package com.worldline.apigouvfr.ban.parameter

/**
 * Search parameters
 *
 * @property query Search query
 * @property limit Number of items returned
 * @property autocomplete Enables/disables auto-completion processing
 * @property type Type of expected result
 * @property latitude Latitude (in degrees). Example: "50.5674052"
 * @property longitude Longitude (in degrees). Example: "3.0287678"
 */
public class BanSearchParameters(
    public val query: String,
    public val limit: Int? = null,
    public val autocomplete: Boolean? = null,
    public val type: Type? = null,
    public val latitude: String? = null,
    public val longitude: String? = null
)
