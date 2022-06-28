package com.worldline.apigouvfr.ban.exception

import com.worldline.apigouvfr.ban.BanResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal suspend inline fun <reified T> safeApiCall(
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline call: suspend () -> T
): BanResult<T> = withContext(coroutineDispatcher) {
    try {
        val success = call.invoke()
        BanResult.Success(success)
    } catch (banException: BanException) {
        BanResult.ServerError(httpCode = banException.httpCode)
    } catch (e: Exception) {
        BanResult.ClientError()
    }
}
