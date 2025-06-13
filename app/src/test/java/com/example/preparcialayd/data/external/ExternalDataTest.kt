package com.example.preparcialayd.data.external

import io.mockk.mockk
import org.junit.Test

class ExternalDataTest{
    private val priceParser : PriceParser = mockk()
    private val apiClient : ApiClient = mockk()
    private val externalData = ExternalData(priceParser,apiClient)

    @Test
    fun `on getResult`(){

    }
}