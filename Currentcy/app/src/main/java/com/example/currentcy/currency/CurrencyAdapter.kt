package com.example.currentcy.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currentcy.databinding.CurrencyStyleBinding
import java.util.*

class CurrencyAdapter(private val adapterInfo: CurrencyAdapterInfo) :
    ListAdapter<CurrencyData, CurrencyAdapter.ViewHolder>(
        ShowNotesDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.bind(currentItem, adapterInfo)
    }

    class ViewHolder(val binding: CurrencyStyleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: CurrencyData, adapterInfo: CurrencyAdapterInfo) {
            binding.noteTitle.text = currentItem.name

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

class ShowNotesDiffCallback : DiffUtil.ItemCallback<CurrencyData>() {
    override fun areItemsTheSame(oldItem: CurrencyData, newItem: CurrencyData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CurrencyData, newItem: CurrencyData): Boolean {
        return oldItem == newItem
    }
}

interface CurrencyAdapterInfo {
    fun editItem(currentItem: CurrencyData)
}