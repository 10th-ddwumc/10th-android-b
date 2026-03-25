package com.umc.workbook.week2

data class WishlistData(
    val image: Int,
    val name: String,
    val subtitle: String? = null,
    val colors: String? = null,
    val price: String,
)
