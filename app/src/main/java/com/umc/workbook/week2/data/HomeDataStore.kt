package com.umc.workbook.week2.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.umc.workbook.week2.domain.repository.LocalHomeRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_store")

@Singleton
class HomeDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) : LocalHomeRepository {

    private val gson = Gson()
    private val NEW_PRODUCT_KEY = stringPreferencesKey("new_product_list")

    override suspend fun saveNewProducts(products: List<NewProductData>) {
        val json = gson.toJson(products)
        context.dataStore.edit {
            it[NEW_PRODUCT_KEY] = json
        }
    }

    override fun getNewProducts(): Flow<List<NewProductData>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[NEW_PRODUCT_KEY] ?: return@map emptyList()
            val listType = object : TypeToken<List<NewProductData>>() {}.type
            gson.fromJson(json, listType)
        }
    }
}