package com.example.preparcialayd.data.external

import com.google.gson.Gson
import com.google.gson.JsonObject

interface PriceParser{
    fun parsePrice(json: String, symbol: String): Double
}

class PriceParserImpl : PriceParser {
    override fun parsePrice(json: String,symbol: String): Double{
        val jsonObject = Gson().fromJson(json, JsonObject::class.java)
        val data = jsonObject["data"].asJsonObject
        val firstElement = data["1"].asJsonObject
        val quotes = firstElement["quotes"].asJsonObject
        val symbolElement = quotes[symbol].asJsonObject
        return symbolElement["price"].asDouble
    }
}