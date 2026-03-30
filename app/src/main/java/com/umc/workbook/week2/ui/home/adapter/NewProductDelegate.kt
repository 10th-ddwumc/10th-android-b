package com.umc.workbook.week2.ui.home.adapter

import com.umc.workbook.week2.model.ProductData

interface NewProductDelegate {
    fun onItemClicked(product: ProductData)
}