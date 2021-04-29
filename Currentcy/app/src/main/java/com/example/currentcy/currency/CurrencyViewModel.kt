package com.example.currentcy.currency

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.currentcy.currency.currency_list.CurrencyListStorage
import com.orhanobut.hawk.Hawk
import org.json.JSONException


class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    private val _calculateRates = MutableLiveData<Boolean>()
    val calculateRates: LiveData<Boolean>
        get() = _calculateRates

    private val _editList = MutableLiveData<Boolean>()
    val editList: LiveData<Boolean>
        get() = _editList

    private val _fetchData = MutableLiveData<ArrayList<Currencies>>()
    val fetchData : LiveData<ArrayList<Currencies>>
        get() = _fetchData

    private val _fetchDataStarter = MutableLiveData<Boolean>()
    val fetchDataStarter : LiveData<Boolean>
        get() = _fetchDataStarter

    private var currencyList: ArrayList<Currencies>? = ArrayList<Currencies>()
    private var currencyItem: List<Currencies>? = null

    var populaterList = mutableListOf<String>()

    private var currencyListMultiply: ArrayList<Currencies>? = ArrayList<Currencies>()

    private val api_key = "ff4b13a6965fddcc4e972fd82e3a6be9"
    lateinit var localCurrencyList : String

    init {
        Hawk.init(application).build()

        localCurrencyList = Hawk.get("CURRENCY_LIST_KEY", "")

        fetchCurrencies(localCurrencyList)
    }

    fun fetchCurrencies(sentCurrencyList: String) {

        localCurrencyList = sentCurrencyList


        if (localCurrencyList.isNotEmpty()) {
            currencyList?.clear()

            val url =
                "http://api.exchangeratesapi.io/v1/latest?access_key=" + api_key + "&symbols=" + localCurrencyList

            val requestQueue: com.android.volley.RequestQueue? = Volley.newRequestQueue(getApplication())
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
                                populaterList.add(tmpCurrency.name)
                            }

                            _fetchData.value = currencyList
                            Hawk.put("TEST_KEY", populaterList)

                            onFetch()
                            //val adapter = CurrencyAdapter(currencyList)

                            //binding.currencyList.adapter = adapter
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            TODO("ERROR CASE1")
                            Log.e("ERROR: ", "$e")
                        }
                    }) { error ->
                    error.printStackTrace()
                    TODO("ERROR CASE2")
                    Log.e("ERROR", error.toString())
                }

            if (requestQueue != null) {
                requestQueue.add(jsonObjectRequest)
                // TO DO ERROR CASE
            }
        } else {
            //Hawk.put("TEST_KEY", "")
            Hawk.delete("TEST_KEY");
        }
    }

    fun onFetch() {
        _fetchDataStarter.value = true
    }

    fun onFetchReset() {
        _fetchDataStarter.value = false
    }

    fun onCalculate() {
        _calculateRates.value = true
    }

    fun onCalculateReset() {
        _calculateRates.value = false
    }

    fun onEditList() {
        _editList.value = true
    }

    fun onEditListReset() {
        _editList.value = false
    }
}