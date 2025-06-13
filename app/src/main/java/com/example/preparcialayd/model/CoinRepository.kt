package com.example.preparcialayd.model

interface CoinRepository {
    fun fetchPrice(symbol: String): Double
    fun getCoinTypes(): List<String>
}