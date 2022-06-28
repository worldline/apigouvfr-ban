@file:OptIn(ExperimentalCoroutinesApi::class)

package com.worldline.apigouvfr.ban.exception

import com.worldline.apigouvfr.ban.BanResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runTest
import org.junit.Test

internal class SafeApiCallTest {

    private val coroutineDispatcher = StandardTestDispatcher(TestCoroutineScheduler())

    @Test
    fun `SafeApiCall when ok then Success`() = runTest(coroutineDispatcher) {
        val result = safeApiCall(coroutineDispatcher) {
            true
        }

        assert(result is BanResult.Success<*>)
    }

    @Test
    fun `SafeApiCall when BanException then ServerError`() = runTest(coroutineDispatcher) {
        val result = safeApiCall(coroutineDispatcher) {
            throw BanException(0)
            true
        }

        assert(result is BanResult.ServerError<*>)
    }

    @Test
    fun `SafeApiCall when BanException then ClientError`() = runTest(coroutineDispatcher) {
        val result = safeApiCall(coroutineDispatcher) {
            throw IllegalStateException()
            true
        }

        assert(result is BanResult.ClientError<*>)
    }
}
