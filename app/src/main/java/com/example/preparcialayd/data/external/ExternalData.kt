package com.example.preparcialayd.data.external

class ExternalData(
    val priceParser: PriceParser,
    val apiClient: ApiClient
) {

    private val url = "https://api.alternative.me/v2/ticker/1/?convert="

    fun getResult(symbol: String): Double {
        val fullUrl = url + symbol
        val json = apiClient.getJsonFromUrl(fullUrl)
        return priceParser.parsePrice(json,symbol)
    }
}