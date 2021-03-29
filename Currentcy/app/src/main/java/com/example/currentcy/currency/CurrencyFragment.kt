package com.example.currentcy.currency

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.currentcy.R
import com.example.currentcy.databinding.FragmentCurrencyBinding
import org.json.JSONException
import kotlin.collections.ArrayList

var currencyList: ArrayList<Currencies>? = ArrayList<Currencies>()
var currencyItem: List<Currencies>? = null

var currencyListMultiply: ArrayList<Currencies>? = ArrayList<Currencies>()

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

        getPostVolley()

        currencyViewModel.calculateRates.observe(viewLifecycleOwner, Observer {
            if (it) {
//                val toRemoveList: ArrayList<Currencies>? = null

                val multiplicatonRate = binding.currencyInput.text.toString()

                if (!multiplicatonRate.isEmpty()) {
                    for (elements in currencyList!!) {
                        val tmpCurrencyName = elements.name

                        val tmpCurrencyRate =
                            elements.rate.toDouble() * multiplicatonRate.toDouble()

                        currencyList?.find { it.name == elements.name }?.rate =
                            tmpCurrencyRate.toString()

//                    currencyList!!.remove(elements)

                        val tmpCurrency = Currencies(tmpCurrencyName, tmpCurrencyRate.toString())

//                        toRemoveList?.add(tmpCurrency)
                        currencyListMultiply!!.add(tmpCurrency)

                        val adapter = CurrencyAdapter(currencyListMultiply)
//                        adapter.notifyDataSetChanged()


                        binding.currencyList.adapter = adapter
                    }

                    currencyViewModel.onCalculateReset()
                } else {
                    Toast.makeText(context, "Multiplication rate not set!", Toast.LENGTH_SHORT)
                        .show()
                }

//                toRemoveList?.let { it1 -> currencyListMultiply?.removeAll(it1) }

            }

        })
    }

    fun getPostVolley() {

        Log.e("STARTED: ", "NOW")

        val url = "https://api.exchangeratesapi.io/latest?base=EUR"

        val requestQueue: com.android.volley.RequestQueue? = Volley.newRequestQueue(context)
        val jsonObjectRequest =
            JsonObjectRequest(GET, url, null,
                { response ->
                    try {
                        val jsonArray = response.getJSONObject("rates")
                        val keys = jsonArray.keys()

                        currencyItem = ArrayList<Currencies>()

                        while (keys.hasNext()) {
                            val key = keys.next()
                            val value = jsonArray.optString(key)

                            val tmpCurrency = Currencies(key, value)

                            currencyList!!.add(tmpCurrency)
                        }
                        val adapter = CurrencyAdapter(currencyList)

                        binding.currencyList.adapter = adapter
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Log.e("ERROR: ", "$e")
                    }
                }) { error -> error.printStackTrace() }

        if (requestQueue != null) {
            requestQueue.add(jsonObjectRequest)
        }
    }

//    override fun editItem(currentItem: CurrencyData) {
//        TODO("Not yet implemented")
//    }
}