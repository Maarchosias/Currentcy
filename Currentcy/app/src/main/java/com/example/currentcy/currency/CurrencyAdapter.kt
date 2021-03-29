package com.example.currentcy.currency

import android.annotation.SuppressLint
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
class CurrencyAdapter(val myList: ArrayList<Currencies>?) :
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
        myList?.get(position)?.let { holder?.bind(it) }
//        val currentItem = getItem(position)
//
//        holder.bind(currentItem)
    }

    class ViewHolder(val binding: CurrencyStyleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: Currencies) {
            binding.currencyTitle.text = currentItem.name

            
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

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Currencies, newItem: Currencies): Boolean {
        return oldItem == newItem
    }
}

//interface CurrencyAdapterInfo {
//    fun editItem(currentItem: CurrencyData)
//}