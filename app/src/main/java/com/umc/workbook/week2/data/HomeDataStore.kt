package com.umc.workbook.week2.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name="app_store")

class HomeDataStore(private val context: Context) {
    private val gson = Gson()
    private val NEW_PRODUCT_KEY = stringPreferencesKey("new_product_list")

    suspend fun saveNewProducts(products: List<NewProductData>){
        val json = gson.toJson(products)
        context.dataStore.edit {
            it[NEW_PRODUCT_KEY] = json
        }
    }

    fun getNewProducts():Flow<List<NewProductData>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[NEW_PRODUCT_KEY] ?: return@map emptyList()
            val listType = object: TypeToken<List<NewProductData>>() {}.type
            gson.fromJson(json, listType)
        }
    }
}