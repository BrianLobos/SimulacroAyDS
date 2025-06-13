package com.example.preparcialayd.presentation

import com.example.preparcialayd.model.CoinRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class CoinPresenterImplTest{
    private val repository: CoinRepository = mockk()
    private lateinit var coinPresenter: CoinPresenter

    @Before
    fun onBefore(){
        every{ repository.getCoinTypes()} returns emptyList()
        coinPresenter = CoinPresenterImpl(repository)
    }

    @Test
    fun `fetchPrice should return price for selected coin`(){
        every { repository.fetchPrice("USD") } returns 123.45
        val coinPriceTester: (Pair<String,Int>) -> Unit = mockk(relaxed = true)
        coinPresenter.coinObservable.subscribe(coinPriceTester)

        coinPresenter.fetchPrice("USD")
        verify { coinPriceTester(Pair("USD",123.45.toInt())) }
    }
}
