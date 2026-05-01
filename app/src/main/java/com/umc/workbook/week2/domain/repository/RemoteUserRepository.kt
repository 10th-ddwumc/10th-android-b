package com.umc.workbook.week2.domain.repository

import com.umc.workbook.week2.data.UserData

interface RemoteUserRepository {
    suspend fun getUser(): Result<UserData>
    suspend fun getUserList(): Result<List<UserData>>
}