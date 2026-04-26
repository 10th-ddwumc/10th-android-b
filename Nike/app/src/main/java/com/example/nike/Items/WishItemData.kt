package com.example.nike.Items

data class WishItemData(
    val id: String,     //상품 구분 id
    val imageRes: Int,   // drawable 리소스
    val name: String,
    val price: String,
    val explan: String = "",
    val bestSell: String = "",
)