package com.example.currentcy.currency

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currentcy.R
import com.example.currentcy.databinding.CurrencyStyleBinding
import kotlin.coroutines.coroutineContext

//private var currencyList: List<CurrencyData>, private val adapterInfo: CurrencyAdapterInfo
class CurrencyAdapter(private val myList: ArrayList<Currencies>?) :
    ListAdapter<Currencies, CurrencyAdapter.ViewHolder>(
        ShowNotesDiffCallback()
    ) {

    override fun getItemCount(): Int {
        return myList!!.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        myList?.get(position)?.let { holder?.bind(it) }
        val currentItem = myList?.get(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class ViewHolder(val binding: CurrencyStyleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: Currencies) {
            binding.currencyTitle.text = currentItem.name
            binding.currencyRate.text = currentItem.rate

            // context for the currencyType
            val currencyTypeContext = binding.currencyType.context

            if(currentItem.name == "CAD") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_cad))
            } else if(currentItem.name == "HKD") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_hkd))
            } else if(currentItem.name == "ISK") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_isk))
            } else if(currentItem.name == "PHP") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_php))
            } else if(currentItem.name == "DKK") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_dkk))
            } else if(currentItem.name == "HUF") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_huf))
            } else if(currentItem.name == "CZK") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_czk))
            } else if(currentItem.name == "AUD") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_aud))
            } else if(currentItem.name == "RON") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_ron))
            } else if(currentItem.name == "SEK") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_sek))
            } else if(currentItem.name == "IDR") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_idr))
            } else if(currentItem.name == "INR") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_inr))
            } else if(currentItem.name == "BRL") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_brl))
            } else if(currentItem.name == "RUB") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_rub))
            } else if(currentItem.name == "HRK") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_hrk))
            } else if(currentItem.name == "JPY") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_jpy))
            } else if(currentItem.name == "THB") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_thb))
            } else if(currentItem.name == "CHF") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_chf))
            } else if(currentItem.name == "SGD") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_sgd))
            } else if(currentItem.name == "PLN") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_pln))
            } else if(currentItem.name == "BGN") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_bgn))
            } else if(currentItem.name == "TRY") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_try))
            } else if(currentItem.name == "CNY") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_cny))
            } else if(currentItem.name == "NOK") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_nok))
            } else if(currentItem.name == "NZD") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_nzd))
            } else if(currentItem.name == "ZAR") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_zar))
            } else if(currentItem.name == "USD") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_usd))
            } else if(currentItem.name == "MXN") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_mxn))
            } else if(currentItem.name == "ILS") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_ils))
            } else if(currentItem.name == "GBP") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_gbp))
            } else if(currentItem.name == "KRW") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_krw))
            } else if(currentItem.name == "MYR") {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_myr))
            } else {
                binding.currencyType.setImageDrawable(ContextCompat.getDrawable(currencyTypeContext, R.mipmap.ic_blank))
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CurrencyStyleBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}

class ShowNotesDiffCallback : DiffUtil.ItemCallback<Currencies>() {
    override fun areItemsTheSame(oldItem: Currencies, newItem: Currencies): Boolean {
        return oldItem.name == newItem.name
    }

//    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Currencies, newItem: Currencies): Boolean {
        return oldItem == newItem
    }
}

//interface CurrencyAdapterInfo {
//    fun editItem(currentItem: CurrencyData)
//}