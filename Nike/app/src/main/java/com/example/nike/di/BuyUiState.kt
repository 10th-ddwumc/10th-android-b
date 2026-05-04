package com.example.nike.com.example.nike.di

import com.example.nike.View.Ui.Base.UiState
import com.example.nike.com.example.nike.data.model.BuyItemData

data class BuyUiState(
    val buyItems: List<BuyItemData> = emptyList()
) : UiState