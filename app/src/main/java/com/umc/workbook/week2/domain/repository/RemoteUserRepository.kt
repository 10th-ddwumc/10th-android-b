package com.umc.workbook.week2.domain.repository

import com.umc.workbook.week2.data.UserData

interface RemoteUserRepository {
    suspend fun getUser(apiKey: String): Result<UserData>
    suspend fun getUserList(apiKey: String): Result<List<UserData>>
}