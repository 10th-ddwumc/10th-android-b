package com.umc.workbook.week2.domain.repository

import com.umc.workbook.week2.data.ShopProductData
import kotlinx.coroutines.flow.Flow

interface LocalShopRepository {
    fun getShopProducts(): Flow<List<ShopProductData>>
    suspend fun saveShopProducts(products: List<ShopProductData>)
}