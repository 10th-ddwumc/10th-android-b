package com.example.nike.data.repository

import com.example.nike.Profit.UserData
import com.example.nike.data.Remote.ReqresApi
import jakarta.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val api: ReqresApi
) : ProfileRepository {

    override suspend fun getUser(id: Int): UserData {
        return api.getUser(id).data
    }

    override suspend fun getUserList(): List<UserData> {
        return api.getUserList().data
    }
}