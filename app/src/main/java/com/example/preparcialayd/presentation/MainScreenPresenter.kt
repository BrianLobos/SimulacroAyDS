package com.example.preparcialayd.presentation

import android.content.Context
import ayds.observer.Subject
import com.example.preparcialayd.data.CoinRepository
import kotlin.concurrent.thread
import kotlin.math.roundToInt

class MainScreenPresenter(context: Context) {

    val coinObservable = Subject<Pair<String, Int>>()
    private val coinRepository = CoinRepository(context)

    fun fetchPriceAsync(selectedCoin: String) {
        thread {
            fetchPrice(selectedCoin)
        }
    }

    private fun fetchPrice(selectedCoin: String){
        val price = coinRepository.fetchPrice(selectedCoin)
        coinObservable.notify(Pair(selectedCoin, price.roundToInt()))
    }

}