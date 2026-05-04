package com.example.nike.data.repository

import com.example.nike.Profit.UserData

interface ProfileRepository {
    suspend fun getUser(id: Int): UserData
    suspend fun getUserList(): List<UserData>
}