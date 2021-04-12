package com.example.currentcy.convert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currentcy.R
import com.example.currentcy.databinding.FragmentConvertBinding


class ConvertFragment: Fragment() {

    lateinit var binding: FragmentConvertBinding
    lateinit var convertViewModel: ConvertViewModel

    private val currencyList = arrayOf("CANADA", "ROMANIA", "USA")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_convert, container, false)
        convertViewModel = ViewModelProvider(this).get(ConvertViewModel::class.java)

        binding.convertViewModel = convertViewModel

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val adapter: ArrayAdapter<String>? = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_dropdown_item_1line, currencyList) }

        binding.currencyFrom.setAdapter(adapter)

    }
}