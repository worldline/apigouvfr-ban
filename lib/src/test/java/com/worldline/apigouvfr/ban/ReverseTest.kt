package com.worldline.apigouvfr.ban

import com.worldline.apigouvfr.ban.datasource.BanRepositoryImpl
import com.worldline.apigouvfr.ban.mock.ApiMockEngine
import com.worldline.apigouvfr.ban.mock.defaultBanFeatureCollectionResponse
import com.worldline.apigouvfr.ban.parameter.BanReverseParameters
import io.ktor.http.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
public class ReverseTest {

    private val coroutineDispatcher = StandardTestDispatcher(TestCoroutineScheduler())
    private lateinit var banRepositoryImpl: BanRepositoryImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T : Any> mockHttpClient(
        httpStatusCode: HttpStatusCode,
        response: T? = null
    ) {
        banRepositoryImpl =
            BanRepositoryImpl(coroutineDispatcher, apiMockEngine(httpStatusCode, response))
    }

    private fun mockHttpClientForClientError() {
        banRepositoryImpl = BanRepositoryImpl(coroutineDispatcher, apiMockEngine.error())
    }

    // endregion Mocks & tools

    @Test
    public fun `reverse when parameters ok then Success`(): TestResult =
        runTest(coroutineDispatcher) {
            // Given
            mockHttpClient(HttpStatusCode.OK, defaultBanFeatureCollectionResponse)

            // When
            val result = banRepositoryImpl.reverse(
                BanReverseParameters(
                    latitude = "50.5674052",
                    longitude = "3.0287678"
                )
            )

            // Then
            assert(result is BanResult.Success)
        }

    @Test
    public fun `reverse when HTTP 404 then ServerError`(): TestResult =
        runTest(coroutineDispatcher) {
            // Given
            mockHttpClient<Any>(HttpStatusCode.NotFound)

            // When
            val result = banRepositoryImpl.reverse(
                BanReverseParameters(
                    latitude = "50.5674052",
                    longitude = "3.0287678"
                )
            )

            // Then
            assert(result is BanResult.ServerError)
        }

    @Test
    public fun `reverse when client error then ClientError`(): TestResult =
        runTest(coroutineDispatcher) {
            // Given
            mockHttpClientForClientError()

            // When
            val result = banRepositoryImpl.reverse(
                BanReverseParameters(
                    latitude = "50.5674052",
                    longitude = "3.0287678"
                )
            )

            // Then
            assert(result is BanResult.ClientError)
        }
}
