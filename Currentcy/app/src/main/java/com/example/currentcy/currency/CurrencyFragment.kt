package com.example.currentcy.currency

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.currentcy.R
import com.example.currentcy.currency.currency_list.CurrencyListActivity
import com.example.currentcy.databinding.FragmentCurrencyBinding
import com.orhanobut.hawk.Hawk

// CurrencyAdapterInfo
class CurrencyFragment : Fragment() {

    lateinit var binding: FragmentCurrencyBinding
    lateinit var currencyViewModel: CurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_currency, container, false)
        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)

        binding.currencyViewModel = currencyViewModel

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStart() {
        super.onStart()

        Hawk.init(context).build()

//        val details = CurrencyFragmentArgs.fromBundle(requireArguments()).transferedCurrencyList

//        if(details != null) {
////            finish();
////            startActivity(getIntent());
//
//            //currencyViewModel.fetchCurrencies(details)
//            //val adapter = CurrencyAdapter(details)
//
//            //binding.currencyList.adapter = adapter
//        }

        currencyViewModel.fetchData.observe(viewLifecycleOwner, Observer {
            val adapter = CurrencyAdapter(it)

            binding.currencyList.adapter = adapter

            currencyViewModel.onFetchReset()
        })

        currencyViewModel.editList.observe(viewLifecycleOwner, Observer {
            if (it) {
                val intent = Intent(context, CurrencyListActivity::class.java)

                startActivity(intent)
                currencyViewModel.onEditListReset()
            }
        })
    }
}