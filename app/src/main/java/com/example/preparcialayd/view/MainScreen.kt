package com.example.preparcialayd.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.preparcialayd.presentation.MainScreenPresenter
import com.example.preparcialayd.R

class MainScreen : AppCompatActivity() {
    private lateinit var mainScreenPresenter: MainScreenPresenter
    private lateinit var spinner: Spinner
    private lateinit var priceText: TextView

    private val coinTypes = listOf("USD", "EUR", "CAD", "JPY", "RUB", "GBP", "KRW", "PLN")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewProperties()
        initPresenter()
        configureSpinner()
    }

    fun initViewProperties(){
        setContentView(R.layout.activity_main)
        spinner = findViewById(R.id.spinnerMonedas)
        priceText = findViewById(R.id.textPrecio)
    }

    fun initPresenter(){
        mainScreenPresenter = MainScreenPresenter(this)
        mainScreenPresenter.observer.subscribe { result ->
            onPrice("$result.first – $result.second")
        }
    }

    fun configureSpinner(){
        setSpinnerAdapter()
        setSpinnerListener()
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

    fun onPrice(price: String) {
        runOnUiThread {
            priceText.text = price
        }
    }
}