package com.example.nike.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import androidx.datastore.preferences.core.*
import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import com.example.nike.com.example.nike.data.model.BuyItemData
import com.example.nike.com.example.nike.data.model.HomeItemData
import com.example.nike.com.example.nike.data.model.WishItemData
import kotlinx.coroutines.flow.first

class ItemDataManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()

    private val BUY_KEY = stringPreferencesKey("buy_items")
    private val HOME_KEY = stringPreferencesKey("home_items")
    private val WISH_KEY = stringPreferencesKey("wishlist")

    // ---------------- BUY ----------------
    suspend fun saveBuyItems(list: List<BuyItemData>) {
        save(BUY_KEY, list)
    }

    fun getBuyItems(): Flow<List<BuyItemData>> {
        return context.dataStore.data.map {
            val json = it[BUY_KEY] ?: "[]"
            val type = object : TypeToken<List<BuyItemData>>() {}.type
            gson.fromJson<List<BuyItemData>>(json, type) ?: emptyList()
        }
    }

    // ---------------- HOME ----------------
    suspend fun saveHomeItems(list: List<HomeItemData>) {
        save(HOME_KEY, list)
    }

    fun getHomeItems(): Flow<List<HomeItemData>> {
        return context.dataStore.data.map {
            val json = it[HOME_KEY] ?: "[]"
            val type = object : TypeToken<List<HomeItemData>>() {}.type
            gson.fromJson<List<HomeItemData>>(json, type) ?: emptyList()
        }
    }

    // ---------------- WISH ----------------
    suspend fun saveWishItems(list: List<WishItemData>) {
        save(WISH_KEY, list)
    }

    fun getWishItems(): Flow<List<WishItemData>> {
        return context.dataStore.data.map {
            val json = it[WISH_KEY] ?: "[]"
            val type = object : TypeToken<List<WishItemData>>() {}.type
            gson.fromJson<List<WishItemData>>(json, type) ?: emptyList()
        }
    }

    // ---------------- 저장 및 불러오기를 공통 사용 (범용 함수) ----------------
    private suspend fun <T> save(
        key: Preferences.Key<String>,
        data: List<T>
    ) {
        context.dataStore.edit {
            it[key] = gson.toJson(data)
        }
    }

    //각자
//    private inline fun <reified T> get(
//        key: Preferences.Key<String>
//    ): Flow<List<T>> {
//        return context.dataStore.data.map {
//            val json = it[key] ?: "[]"
//            val type = object : TypeToken<List<T>>() {}.type
//            gson.fromJson(json, type)
//        }
//    }

    // ----------------- Buy 전용 ----------------
    suspend fun toggleWishItem(itemId: String) {
        val currentBuyList = getBuyItems().first()
        val currentWishList = getWishItems().first().toMutableList()

        val targetItem = currentBuyList.find { it.id == itemId } ?: return

        val updatedBuyList = currentBuyList.map { item ->
            if (item.id == itemId) {
                item.copy(isWishlisted = !item.isWishlisted)
            } else {
                item
            }
        }

        if (targetItem.isWishlisted) {
            currentWishList.removeAll { it.id == itemId }
        } else {
            currentWishList.add(
                WishItemData(
                    id = targetItem.id,
                    imageRes = targetItem.imageRes,
                    name = targetItem.name,
                    price = targetItem.price,
                    explan = targetItem.explan
                )
            )
        }

        saveBuyItems(updatedBuyList)
        saveWishItems(currentWishList)
    }
}