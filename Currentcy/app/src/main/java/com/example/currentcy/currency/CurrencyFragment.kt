package com.example.currentcy.currency

import android.net.http.RequestQueue
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request.Method.GET
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.currentcy.R
import com.example.currentcy.databinding.FragmentCurrencyBinding
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONException
import java.io.IOException
import kotlin.collections.ArrayList

class dataStorage(key: String, value: String) {
    val key: String? = null
    val value: Double? = null
}

val chapterList: ArrayList<String> = ArrayList<String>()

var currencyList: ArrayList<Currencies>? = ArrayList<Currencies>()
var currencyItem: List<Currencies>? = null

// CurrencyAdapterInfo
class CurrencyFragment : Fragment() {

    lateinit var binding: FragmentCurrencyBinding
    lateinit var currencyViewModel: CurrencyViewModel

//    val movies = listOf<CurrencyData>(CurrencyData("HUF", "3.12"))

//    private val adapter = CurrencyAdapter(myList)

    //    private lateinit var currencyList: ArrayList<CurrencyData>
    private lateinit var requestQueue: RequestQueue

    private val URL = "https://api.exchangeratesapi.io/latest?base=RON"

    internal var json = ""
    internal lateinit var list: ArrayList<Currencies>
    internal lateinit var listView: ListView
//    var myList: ArrayList<String>? = null

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
//
                        val adapter = CurrencyAdapter(currencyList)

                        binding.currencyList.adapter = adapter
//                        }
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