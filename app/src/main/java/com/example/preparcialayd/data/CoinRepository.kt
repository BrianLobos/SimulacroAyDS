package com.example.preparcialayd.data

import com.example.preparcialayd.data.external.ExternalData
import com.example.preparcialayd.model.CoinRepository


class CoinRepositoryImpl(
    private val localData: LocalData,
    private val externalData: ExternalData
) :CoinRepository{

    override fun fetchPrice(symbol: String): Double {
        val savedValue = localData.getResult(symbol)
        if(savedValue != null) {
            return savedValue
        } else {
            val result = externalData.getResult(symbol)
            localData.setResult(symbol,result)
            return result
        }
    }

    override fun getCoinTypes(): List<String> = listOf("USD", "EUR", "CAD", "JPY", "RUB", "GBP", "KRW", "PLN")
}