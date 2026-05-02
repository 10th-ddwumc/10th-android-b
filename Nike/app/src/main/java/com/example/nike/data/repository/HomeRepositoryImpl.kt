package com.example.nike.data.repository

import com.example.nike.com.example.nike.data.model.HomeItemData
import com.example.nike.data.local.ItemDataManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val itemDataManager: ItemDataManager
) : HomeRepository {

    override fun getHomeItems(): Flow<List<HomeItemData>> {
        return itemDataManager.getHomeItems()
    }

    override suspend fun saveHomeItems(items: List<HomeItemData>) {
        itemDataManager.saveHomeItems(items)
    }

    override suspend fun initHomeItemsIfEmpty(defaultItems: List<HomeItemData>) {
        val currentList = itemDataManager.getHomeItems().first()

        if (currentList.isEmpty()) {
            itemDataManager.saveHomeItems(defaultItems)
        }
    }
}