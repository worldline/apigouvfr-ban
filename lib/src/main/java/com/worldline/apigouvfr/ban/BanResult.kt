package com.worldline.apigouvfr.ban

/**
 * Object returned by [BanService]
 */
public sealed class BanResult<T> {

    /**
     * An error occurred in the client side of the library
     */
    public class ClientError<T> : BanResult<T>()

    /**
     * An error occurred in the backend side
     *
     * @property httpCode HTTP request return code
     * @property message (optional) Additional message
     */
    public class ServerError<T>(public val httpCode: Int, public val message: String? = null) : BanResult<T>()

    /**
     * Result in success
     *
     * @property data Returned data
     */
    public class Success<T>(public val data: T) : BanResult<T>()
}
