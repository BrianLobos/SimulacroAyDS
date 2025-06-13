package com.example.preparcialayd.data

import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Test

class LocalDataImplTest{
    private val sharedPreferences : SharedPreferences = mockk()
    private val localDataImpl: LocalData = LocalDataImpl(sharedPreferences)

    @Test
    fun `if symbol is saved, return value`(){
        every { sharedPreferences.getString("value",null) } returns "123"

        val result = localDataImpl.getResult("value")

        Assert.assertEquals(result,"123".toDouble())
    }

    @Test
    fun `if symbol is not saved, return null`(){
        every{ sharedPreferences.getString(any(),any())} returns null

        val result = localDataImpl.getResult("symbol")

        Assert.assertEquals(result,null)
    }
}