package com.example.nike.Coroutine

import androidx.datastore.preferences.preferencesDataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.nike.Items.BuyItemData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//Context 확장 함수 선언
public val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "itemStore")

class BuyItemDataManager(private val context: Context) {
    //데이터 key(식별)
    val BUY_ITEM_KEY = stringPreferencesKey("buy_items")
    private val gson = Gson()

    // 데이터 저장하기
    suspend fun saveBuyItem(buyItemData : List<BuyItemData>) {
        context.dataStore.edit { settings ->
            // 지정한 key와 일치하는 저장소에 name 값을 저장합니다.
            // //객체 -> json
            val jsonString = gson.toJson(buyItemData)
            settings[BUY_ITEM_KEY] = jsonString
        }
    }

    // 데이터 가져오기
    fun getBuyItem(): Flow<List<BuyItemData>> {
        return context.dataStore.data.map { preferences ->
            // 저장된 값이 없으면 "이름 없음"을 반환합니다.
            // 항상 항상 nullable하게 대처를 해야합니다.
            val jsonString = preferences[BUY_ITEM_KEY] ?: "[]"
            val type = object : TypeToken<List<BuyItemData>>() {}.type
            //json -> 객체
            gson.fromJson(jsonString, type)
        }
    }

    // 위시 전환
    suspend fun toggleWishItem(itemId: String) {
        val currentList = getBuyItem().first() // .first() 가장 최신상태가져오기

        val updatedList = currentList.map { item ->
            if (item.id == itemId) {
               //data class는 불변이라 copy 사용(AI 도움)
                item.copy(isWishlisted = !item.isWishlisted)
            } else {
                item
            }
        }
        saveBuyItem(updatedList)
    }
}