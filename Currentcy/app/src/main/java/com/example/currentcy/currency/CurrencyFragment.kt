package com.example.currentcy.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currentcy.R
import com.example.currentcy.databinding.FragmentCurrencyBinding

class CurrencyFragment: Fragment(), CurrencyAdapterInfo {

    lateinit var binding: FragmentCurrencyBinding
    lateinit var currencyViewModel: CurrencyViewModel

    private val adapter = CurrencyAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency, container, false)
        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)

        binding.currencyViewModel = currencyViewModel

        binding.currencyList.adapter = adapter

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        

    }

    override fun editItem(currentItem: CurrencyData) {
        TODO("Not yet implemented")
    }
}