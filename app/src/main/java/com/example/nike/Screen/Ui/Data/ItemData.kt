package com.example.nike.Screen.Ui.Data

data class ItemData(
    //primaryKey 추가
    val id: Int = 0,

    val imageRes: Int,
    val name: String,
    val price: String,
    val explan: String = "",
    val bestSell: String = "",
    var isWishlisted: Boolean = false
)