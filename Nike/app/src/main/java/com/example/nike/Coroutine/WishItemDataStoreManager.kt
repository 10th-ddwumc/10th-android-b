package com.example.nike.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.nike.Coroutine.dataStore
import com.example.nike.Items.WishItemData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class WishItemDataStoreManager(private val context: Context) {
    companion object {
        val WISHLIST_KEY = stringPreferencesKey("wishlist")
    }
        private val gson = Gson()

        // 데이터 저장하기
        suspend fun saveWishItem(wishItemData : List<WishItemData>) {
            context.dataStore.edit { settings ->
                // 지정한 key와 일치하는 저장소에 name 값을 저장합니다.
                // 객체 -> json
                val jsonString = gson.toJson(wishItemData)
                settings[WISHLIST_KEY] = jsonString
            }
        }

        // 데이터 가져오기
        fun getWishItem(): Flow<List<WishItemData>> {
            return context.dataStore.data.map { preferences ->
                // 저장된 값이 없으면 "이름 없음"을 반환합니다.
                // 항상 항상 nullable하게 대처를 해야합니다.
                val jsonString = preferences[WISHLIST_KEY] ?: "[]"
                val type = object : TypeToken<List<WishItemData>>() {}.type
                //json -> 객체
                gson.fromJson(jsonString, type)
            }
        }
}
