package com.umc.workbook.week2.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShopDataStore(private val context: Context) {

    private val gson = Gson()
    private val SHOP_PRODUCT_KEY = stringPreferencesKey("shop_product_list")

    suspend fun saveShopProducts(products: List<ShopProductData>) {
        val json = gson.toJson(products)
        context.dataStore.edit {
            it[SHOP_PRODUCT_KEY] = json
        }
    }

    fun getShopProducts(): Flow<List<ShopProductData>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[SHOP_PRODUCT_KEY] ?: return@map emptyList()
            val listType = object : TypeToken<List<ShopProductData>>() {}.type
            gson.fromJson(json, listType)
        }
    }
}