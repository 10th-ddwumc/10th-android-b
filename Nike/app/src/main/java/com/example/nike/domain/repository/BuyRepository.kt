package com.example.nike.com.example.nike.domain.repository


import com.example.nike.com.example.nike.data.model.BuyItemData
import com.example.nike.com.example.nike.data.model.HomeItemData
import kotlinx.coroutines.flow.Flow

interface BuyRepository {
    fun getBuyItems(): Flow<List<BuyItemData>>

    suspend fun saveBuyItems(items: List<BuyItemData>)

    suspend fun initBuyItemsIfEmpty(defaultItems: List<BuyItemData>)
    suspend fun toggleWishItem(itemId: String)
}