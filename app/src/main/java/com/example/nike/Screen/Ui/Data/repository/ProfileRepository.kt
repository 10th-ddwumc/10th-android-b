package com.example.nike.Screen.Ui.Data.repository

import com.example.nike.Screen.Ui.Data.UserData

interface ProfileRepository {

    suspend fun getUser(id: Int): UserData

    suspend fun getUserList(): List<UserData>
}