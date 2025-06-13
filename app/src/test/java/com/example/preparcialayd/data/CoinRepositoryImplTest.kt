package com.example.preparcialayd.data

import com.example.preparcialayd.data.external.ExternalData
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class CoinRepositoryImplTest{

    /*
    override fun fetchPrice(symbol: String): Double {
        val savedValue = localData.getResult(symbol)
        if(savedValue != null) {
            return savedValue
        } else {
            val result = externalData.get(symbol)
            localData.setResult(symbol,result)
            return result
        }
    }*/

    private val localData: LocalData = mockk()
    private val externalData: ExternalData = mockk()
    private val coinRepository = CoinRepositoryImpl(localData,externalData)


    @Test
    fun `fetchPrice returns saved value from localData if present`() {
        every { localData.getResult("USD") } returns 123.45

        val result = coinRepository.fetchPrice("USD")

        Assert.assertEquals(123.45, result,0.0001)
        verify(exactly = 0) { externalData.getResult(any()) }
    }

    @Test
    fun `fetchPrice return value from api if not present in local storage`(){
        every { localData.getResult("USD") } returns null
        every{ externalData.getResult("USD")} returns 123.45
        every {localData.setResult("USD",123.45)} just runs
        val result = coinRepository.fetchPrice(("USD"))

        Assert.assertEquals(123.45,result,0.0001)

        verify(exactly = 1){ externalData.getResult("USD")}
    }

    @Test
    fun `fetchPrice save value in localData if it was not present in local data`(){
        every{ localData.getResult("USD")} returns null
        every {localData.setResult("USD",any())} just runs
        every { externalData.getResult("USD") } returns 123.0

        coinRepository.fetchPrice("USD")

        verify (exactly = 1){ localData.setResult("USD",123.0) }
    }
}