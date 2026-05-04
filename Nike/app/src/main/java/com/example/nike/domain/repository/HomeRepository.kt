package com.example.nike.data.repository

import com.example.nike.com.example.nike.data.model.HomeItemData
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getHomeItems(): Flow<List<HomeItemData>>

    suspend fun saveHomeItems(items: List<HomeItemData>)

    suspend fun initHomeItemsIfEmpty(defaultItems: List<HomeItemData>)
}