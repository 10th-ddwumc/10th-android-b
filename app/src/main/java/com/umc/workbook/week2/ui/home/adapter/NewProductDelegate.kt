package com.umc.workbook.week2.ui.home.adapter

import com.umc.workbook.week2.data.NewProductData

interface NewProductDelegate {
    fun onItemClicked(product: NewProductData)
}