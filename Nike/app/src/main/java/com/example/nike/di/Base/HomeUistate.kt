package com.example.nike.View.Ui.Base

import com.example.nike.com.example.nike.data.model.HomeItemData

data class HomeUiState(
    val items: List<HomeItemData> = emptyList()
)