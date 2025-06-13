package com.example.preparcialayd

import android.content.Context
import android.content.SharedPreferences
import com.example.preparcialayd.data.CoinRepositoryImpl
import com.example.preparcialayd.data.LocalDataImpl
import com.example.preparcialayd.data.external.ApiClientImpl
import com.example.preparcialayd.data.external.ExternalData
import com.example.preparcialayd.data.external.PriceParserImpl
import com.example.preparcialayd.presentation.CoinPresenter
import com.example.preparcialayd.presentation.CoinPresenterImpl

object CoinInjector {
    lateinit var presenter: CoinPresenter

    fun init(context: Context){

        val priceParser = PriceParserImpl()
        val ApiClient = ApiClientImpl()
        val externalData = ExternalData(priceParser,ApiClient)

        val coinPricesCache = context.getSharedPreferences("COIN_PRICES_CACHE", Context.MODE_PRIVATE)
        val localData = LocalDataImpl(coinPricesCache)

        val coinRepository = CoinRepositoryImpl(localData,externalData)

        presenter = CoinPresenterImpl(coinRepository)
    }
}