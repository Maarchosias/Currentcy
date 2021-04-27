package com.example.currentcy.currency.currency_list

import android.content.Intent
import com.example.currentcy.R

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.currentcy.MainActivity
import com.example.currentcy.currency.*
import com.orhanobut.hawk.Hawk
import okhttp3.internal.notify
import org.json.JSONException

private lateinit var currencyListRecyclerView: RecyclerView

private var currencyListSelected: ArrayList<Currencies>? = ArrayList<Currencies>()
private var currencyItem: List<Currencies>? = null

var currencyListStorage: ArrayList<CurrencyListStorage>? = ArrayList<CurrencyListStorage>()

private val api_key = "ff4b13a6965fddcc4e972fd82e3a6be9"
lateinit var adapter: CurrencySelectAdapter

class CurrencyListActivity : AppCompatActivity(), CurrencyAdapterChecker {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_list)
    }

    override fun onStart() {
        super.onStart()

        currencyListStorage?.clear()

        currencyListRecyclerView = findViewById<RecyclerView>(R.id.currency_list_select)

        getPostVolley()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        var transferableCurrencyList: String = ""

        for (item: CurrencyListStorage in currencyListStorage!!) {
            if (item.checked == true) {
                transferableCurrencyList += item.currencyName + ","
            }
        }

        val intent = Intent(this@CurrencyListActivity, MainActivity::class.java)

        Hawk.put("CURRENCY_LIST_KEY", transferableCurrencyList)
        //Hawk.put("LOAD_CHECKED_CURRENCIES", populaterList)

        //intent.putExtra("transferableList", transferableCurrencyList)
        startActivity(intent)

    }

    // API call -> fetches all data into a "Currencies" class type of object -> adds it into an ArrayList
    fun getPostVolley() {
        currencyListSelected?.clear()

        val url = "http://api.exchangeratesapi.io/v1/latest?access_key=" + api_key

        val requestQueue: com.android.volley.RequestQueue? =
            Volley.newRequestQueue(applicationContext)
        val jsonObjectRequest =
            JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    try {
                        val jsonArray = response.getJSONObject("rates")
                        val keys = jsonArray.keys()

                        currencyItem = ArrayList<Currencies>()

                        // while the API has elements
                        while (keys.hasNext()) {

                            // get certain elements | key = name & value = rate
                            val key = keys.next()
                            val value = jsonArray.optString(key)

                            val tmpCurrency = Currencies(key, value)

                            // add each Currency into a list
                            currencyListSelected!!.add(tmpCurrency)
                        }

                        copy()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Log.e("ERROR: ", "$e")
                    }
                }) { error ->
                error.printStackTrace()
                Log.e("ERROR", error.toString())
            }

        if (requestQueue != null) {
            requestQueue.add(jsonObjectRequest)
        }
    }

    // Copies the FIRST ArrayList into a SECOND ArrayList -> Sets the SECOND ArrayList to the RecyclerView adapter (the 2ND ArrayList holds a "checked" variable)
    fun copy() {
        val test_list: MutableList<String>? = Hawk.get("TEST_KEY")

        var count = 0
        // read each ELEMENT from the ORIGINAL FETCH list -> add it to an ArrayList
        for (num: Currencies in currencyListSelected!!) {
            currencyListStorage?.add(CurrencyListStorage(count, "", num.name, false))
            count++
        }

        var counter = 0
        for (element: CurrencyListStorage in currencyListStorage!!) {
            if (test_list != null) {
                for (currency in test_list) {
                    if (currency == element.currencyName) {
                        val tmpCurrency = CurrencyListStorage(element.currencyId, "", element.currencyName, true)
                        currencyListStorage!!.set(counter, tmpCurrency)
                    }
                }
                counter++
            }
        }

        adapter = CurrencySelectAdapter(this)
        currencyListRecyclerView.adapter = adapter
    }

    // LAGS TOO DAMN HARD ON DEBUG MODE
    fun editChecker(currentItem: String, adapterChecker: Boolean) {
        for (item: CurrencyListStorage in currencyListStorage!!) {
            if (currentItem == item.currencyName) {
                item.checked = adapterChecker
            }
        }
    }

    override fun editItem(currentItem: String, adapterChecker: Boolean) {
        editChecker(currentItem, adapterChecker)
    }
}