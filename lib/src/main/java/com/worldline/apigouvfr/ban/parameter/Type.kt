package com.worldline.apigouvfr.ban.parameter

/**
 * Searh type
 */
public enum class Type(internal val queryValue: String) {
    Housenumber("housenumber"),
    Street("street"),
    Locality("locality"),
    Municipality("municipality")
}