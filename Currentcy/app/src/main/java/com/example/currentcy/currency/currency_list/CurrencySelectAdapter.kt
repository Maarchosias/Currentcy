package com.example.currentcy.currency.currency_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currentcy.R
import com.example.currentcy.currency.Currencies
import com.example.currentcy.currency.CurrencyAdapter
//import com.example.currentcy.currency.populaterList
import com.example.currentcy.databinding.CurrencyListSelectStyleBinding
//private val myList: ArrayList<CurrencyListStorage>?,
class CurrencySelectAdapter(val adapterChecker: CurrencyAdapterChecker) :
    ListAdapter<CurrencyListStorage, CurrencySelectAdapter.ViewHolder>(
        ShowNotesDiffCallback()
    ) {

    override fun getItemCount(): Int {
//        return myList!!.count()
        return currencyListStorage!!.count()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CurrencySelectAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        myList?.get(position)?.let { holder?.bind(it) }
//        val currentItem = myList?.get(position)
        val currentItem = currencyListStorage?.get(position)

        if (currentItem != null) {
            holder.bind(currentItem, adapterChecker)
        }
    }

    class ViewHolder(val binding: CurrencyListSelectStyleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: CurrencyListStorage, currencyChecker: CurrencyAdapterChecker) {
            binding.currencyTitle.text = currentItem.currencyName

            binding.itemCheckbox.isChecked = currentItem.checked

            binding.itemCheckbox.setOnClickListener {
                if (currentItem.checked) {
                    binding.itemCheckbox.isChecked = false
                    currentItem.checked = false
                    currencyChecker.editItem(currentItem.currencyName, currentItem.checked)
                    //populaterList.remove(currentItem.currencyName)
                } else {
                    binding.itemCheckbox.isChecked = true
                    currentItem.checked = true
                    currencyChecker.editItem(currentItem.currencyName, currentItem.checked)
                    //populaterList.add(currentItem.currencyName)
                }
            }

            // context for the currencyType
            val currencyTypeContext = binding.currencyType.context

            if (currentItem.currencyName == "CAD") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_cad
                    )
                )
            } else if (currentItem.currencyName == "HKD") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_hkd
                    )
                )
            } else if (currentItem.currencyName == "ISK") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_isk
                    )
                )
            } else if (currentItem.currencyName == "PHP") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_php
                    )
                )
            } else if (currentItem.currencyName == "DKK") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_dkk
                    )
                )
            } else if (currentItem.currencyName == "HUF") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_huf
                    )
                )
            } else if (currentItem.currencyName == "CZK") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_czk
                    )
                )
            } else if (currentItem.currencyName == "AUD") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_aud
                    )
                )
            } else if (currentItem.currencyName == "RON") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_ron
                    )
                )
            } else if (currentItem.currencyName == "SEK") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_sek
                    )
                )
            } else if (currentItem.currencyName == "IDR") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_idr
                    )
                )
            } else if (currentItem.currencyName == "INR") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_inr
                    )
                )
            } else if (currentItem.currencyName == "BRL") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_brl
                    )
                )
            } else if (currentItem.currencyName == "RUB") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_rub
                    )
                )
            } else if (currentItem.currencyName == "HRK") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_hrk
                    )
                )
            } else if (currentItem.currencyName == "JPY") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_jpy
                    )
                )
            } else if (currentItem.currencyName == "THB") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_thb
                    )
                )
            } else if (currentItem.currencyName == "CHF") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_chf
                    )
                )
            } else if (currentItem.currencyName == "SGD") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_sgd
                    )
                )
            } else if (currentItem.currencyName == "PLN") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_pln
                    )
                )
            } else if (currentItem.currencyName == "BGN") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_bgn
                    )
                )
            } else if (currentItem.currencyName == "TRY") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_try
                    )
                )
            } else if (currentItem.currencyName == "CNY") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_cny
                    )
                )
            } else if (currentItem.currencyName == "NOK") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_nok
                    )
                )
            } else if (currentItem.currencyName == "NZD") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_nzd
                    )
                )
            } else if (currentItem.currencyName == "ZAR") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_zar
                    )
                )
            } else if (currentItem.currencyName == "USD") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_usd
                    )
                )
            } else if (currentItem.currencyName == "MXN") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_mxn
                    )
                )
            } else if (currentItem.currencyName == "ILS") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_ils
                    )
                )
            } else if (currentItem.currencyName == "GBP") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_gbp
                    )
                )
            } else if (currentItem.currencyName == "KRW") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_krw
                    )
                )
            } else if (currentItem.currencyName == "MYR") {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_myr
                    )
                )
            } else {
                binding.currencyType.setImageDrawable(
                    ContextCompat.getDrawable(
                        currencyTypeContext,
                        R.mipmap.ic_blank
                    )
                )
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CurrencyListSelectStyleBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ShowNotesDiffCallback : DiffUtil.ItemCallback<CurrencyListStorage>() {
    override fun areItemsTheSame(
        oldItem: CurrencyListStorage,
        newItem: CurrencyListStorage
    ): Boolean {
        return oldItem.currencyName == newItem.currencyName
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: CurrencyListStorage,
        newItem: CurrencyListStorage
    ): Boolean {
        return oldItem == newItem
    }
}

interface CurrencyAdapterChecker {
    fun editItem(currentItem: String, adapterChecker: Boolean)
}