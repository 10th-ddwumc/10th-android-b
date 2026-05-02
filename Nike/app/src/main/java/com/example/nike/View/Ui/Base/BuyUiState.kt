package com.example.nike.View.Ui.Base

import com.example.nike.com.example.nike.data.model.BuyItemData

data class BuyUiState(
    val buyItems: List<BuyItemData> = emptyList()
) : UiState