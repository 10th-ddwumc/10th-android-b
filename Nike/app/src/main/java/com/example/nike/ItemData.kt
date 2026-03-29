package com.example.nike

data class ItemData(
    val imageRes: Int,   // drawable 리소스
    val name: String,
    val price: String,
    val explan: String = "",
    val bestSell: String = "",
    var isWishlisted: Boolean = false, //위시
)
