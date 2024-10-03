package com.pokedex.data.datasource.remote.impl

import com.pokedex.data.api.PokedexApi
import com.pokedex.utils.DataSourceTest
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class PokedexRemoteDataSourceImplTest : DataSourceTest() {

    private val dataSource = PokedexRemoteDataSourceImpl(
        api = retrofit.create(PokedexApi::class.java)
    )

    @Test
    fun `should return a pokedex successfully`() = runTest {
        val body = javaClass.classLoader!!
            .getResourceAsStream("pokedex.json")
            .bufferedReader()
            .readText()

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody(body)
        )

        val result = dataSource.getPokedex(
            limit = 150,
            offset = 0
        )

        result.isSuccess shouldBeEqualTo true

        result.onSuccess {
            it.count shouldBeEqualTo 1302
            it.next shouldBeEqualTo null
            it.results.size shouldBeEqualTo 5
        }
    }
}