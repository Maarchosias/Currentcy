package com.example.currentcy.currency.currency_list

import org.jetbrains.annotations.NotNull

data class CurrencyListStorage(
    val currencyId: Int,
    val flag: String = "",
    val currencyName: String = "",
    var checked: Boolean = false
) {

    fun setName(checked: Boolean) {
        this.checked = checked
    }

    fun getName(): Boolean {
        return this.checked
    }
}