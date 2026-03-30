package com.umc.workbook.week2.model

data class ShopProductData(
    val image: Int,
    val name: String,
    val subtitle: String,
    val colors: String,
    val price: String,
    val isBestSeller: Boolean = false,
    val isWishlisted: Boolean = false
)