package com.example.preparcialayd.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preparcialayd.CoinInjector
import com.example.preparcialayd.presentation.CoinPresenter
import com.example.preparcialayd.R

class MainScreen : AppCompatActivity() {
    private lateinit var mainScreenPresenter: CoinPresenter
    private lateinit var spinner: Spinner
    private lateinit var coinTypes: List<String>
    private lateinit var priceText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewProperties()
        initPresenter()
        getCoinTypes()
        observeCoinPrices()
        setSpinnerAdapter()
        setSpinnerListener()

    }

    private fun initViewProperties(){
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinnerMonedas)
        priceText = findViewById(R.id.textPrecio)
    }
    private fun initPresenter(){
        CoinInjector.init(this)
        mainScreenPresenter = CoinInjector.presenter
    }
    private fun getCoinTypes(){
        coinTypes = mainScreenPresenter.coinTypes
    }

    private fun observeCoinPrices(){
        mainScreenPresenter.coinObservable.subscribe { result ->
            val message = "$result.symbol – $result.price"
            onPrice(message)
        }
    }
    fun setSpinnerAdapter(){
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, coinTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun setSpinnerListener(){
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCoin = coinTypes[position]
                mainScreenPresenter.fetchPrice(selectedCoin)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    fun onPrice(message: String) {
        runOnUiThread {
            priceText.text = message
        }
    }
}