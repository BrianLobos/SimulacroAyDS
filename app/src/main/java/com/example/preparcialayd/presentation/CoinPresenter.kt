package com.example.preparcialayd.presentation

import ayds.observer.Observable
import ayds.observer.Subject
import com.example.preparcialayd.model.CoinRepository
import kotlin.concurrent.thread
import kotlin.math.roundToInt

interface CoinPresenter{
    val coinObservable: Observable<Pair<String,Int>>
    val coinTypes: List<String>
    fun fetchPrice(selectedCoin: String)
}
class CoinPresenterImpl(
    private val repository: CoinRepository
):CoinPresenter{

    override val coinObservable = Subject<Pair<String, Int>>()
    override val coinTypes = repository.getCoinTypes()

    override fun fetchPrice(selectedCoin: String) {
        thread {
            val result = repository.fetchPrice(selectedCoin)
            coinObservable.notify(Pair(selectedCoin, result.roundToInt()))
        }
    }

}