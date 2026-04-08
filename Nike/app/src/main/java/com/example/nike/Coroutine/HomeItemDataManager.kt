package com.example.nike.Coroutine

import androidx.datastore.preferences.preferencesDataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.nike.Items.HomeItemData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


class HomeItemDataManager(private val context: Context) {

    companion object {
        val HOME_ITEM_KEY = stringPreferencesKey("home_items")
    }

    private val gson = Gson()

    // 데이터 저장하기
    suspend fun saveHomeItem(homeItemData: List<HomeItemData>) {
        context.dataStore.edit { settings ->
            // 지정한 key와 일치하는 저장소에 name 값을 저장합니다.
            //객체 -> json
            val jsonString = gson.toJson(homeItemData)
            settings[HOME_ITEM_KEY] = jsonString
        }
    }

    // 데이터 가져오기
    fun getHomeItem(): Flow<List<HomeItemData>> {
        return context.dataStore.data.map { preferences ->
            // 저장된 값이 없으면 "이름 없음"을 반환합니다.
            // 항상 항상 nullable하게 대처를 해야합니다.
            val jsonString = preferences[HOME_ITEM_KEY] ?: "[]"
            val type = object : TypeToken<List<HomeItemData>>() {}.type
            //json -> 객체
            gson.fromJson(jsonString, type)
        }
    }
}