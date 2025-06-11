package com.example.preparcialayd.data

import android.content.Context
import androidx.core.content.edit

class CoinRepository(private val context:Context) {
    private val COIN_PRICES_CACHE = "COIN_PRICES_CACHE"

    val coinPricesCache = context.getSharedPreferences(COIN_PRICES_CACHE, Context.MODE_PRIVATE)

    fun fetchPrice(symbol: String): Double {
        val savedValue = getPriceFromCache(symbol)
        var result : Double

        if(savedValue == null){
            result = CoinService().get(symbol)
            coinPricesCache.edit { putString(symbol, result.toString()) }
        }else
            result = savedValue.toDouble()
        return result
    }

    private fun getPriceFromCache(symbol: String) = coinPricesCache.getString(symbol, null)

}