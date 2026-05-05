package com.umc.workbook.week2.domain.repository

import com.umc.workbook.week2.data.NewProductData
import kotlinx.coroutines.flow.Flow

interface LocalHomeRepository {
    fun getNewProducts(): Flow<List<NewProductData>>
    suspend fun saveNewProducts(products: List<NewProductData>)
}