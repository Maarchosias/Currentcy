package com.example.currentcy.currency.currency_list

import com.example.currentcy.R

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.currentcy.currency.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONException

private lateinit var currencyListSelect: RecyclerView

private var currencyList: ArrayList<Currencies>? = ArrayList<Currencies>()
private var currencyItem: List<Currencies>? = null

private var currencyListMultiply: ArrayList<Currencies>? = ArrayList<Currencies>()

private val api_key = "ff4b13a6965fddcc4e972fd82e3a6be9"

class CurrencyListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)
    }

    override fun onStart() {
        super.onStart()

        currencyListSelect = findViewById<RecyclerView>(R.id.currency_list_select)

        getPostVolley()

    }

    fun getPostVolley() {
        val url = "http://api.exchangeratesapi.io/v1/latest?access_key=" + api_key

        val requestQueue: com.android.volley.RequestQueue? = Volley.newRequestQueue(applicationContext)
        val jsonObjectRequest =
            JsonObjectRequest(
                Request.Method.GET, url, null,
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
                        val adapter = CurrencySelectAdapter(currencyList)

                        currencyListSelect.adapter = adapter
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Log.e("ERROR: ", "$e")
                    }
                }) { error -> error.printStackTrace()
                Log.e("ERROR", error.toString())}

        if (requestQueue != null) {
            requestQueue.add(jsonObjectRequest)
        }
    }
}