package com.example.nike.data.repository


import com.example.nike.com.example.nike.data.model.BuyItemData
import com.example.nike.com.example.nike.data.repository.BuyRepository
import com.example.nike.data.local.ItemDataManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class BuyRepositoryImpl @Inject constructor(
    private val dataManager: ItemDataManager
) : BuyRepository {

    override fun getBuyItems(): Flow<List<BuyItemData>> {
        return dataManager.getBuyItems()
    }

    override suspend fun saveBuyItems(items: List<BuyItemData>) {
        dataManager.saveBuyItems(items)
    }

    override suspend fun toggleWishItem(itemId: String) {
        val currentList = dataManager.getBuyItems().first()

        val updatedList = currentList.map { item ->
            if (item.id == itemId) {
                item.copy(isWishlisted = !item.isWishlisted)
            } else {
                item
            }
        }

        dataManager.saveBuyItems(updatedList)
    }

    override suspend fun initBuyItemsIfEmpty(defaultItems: List<BuyItemData>) {
        val currentList = dataManager.getBuyItems().first()

        if (currentList.isEmpty()) {
            dataManager.saveBuyItems(defaultItems)
        }
    }
}