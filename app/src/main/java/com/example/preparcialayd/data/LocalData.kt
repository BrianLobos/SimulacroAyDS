package com.example.preparcialayd.data

import android.content.SharedPreferences
import androidx.core.content.edit

interface LocalData{
    fun getResult(symbol: String):Double?
    fun setResult(symbol:String, result: Double)
}

class LocalDataImpl(
    private val sharedPreferences: SharedPreferences
) : LocalData{

    override fun getResult(symbol: String): Double? {
        val result = sharedPreferences.getString(symbol, null)
        return try {
            result?.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }


    override fun setResult(symbol: String, result: Double) {
        sharedPreferences.edit { putString(symbol, result.toString()) }
    }

}