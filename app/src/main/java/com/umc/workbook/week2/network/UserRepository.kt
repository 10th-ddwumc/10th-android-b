package com.umc.workbook.week2.network

import android.util.Log
import com.umc.workbook.week2.data.UserData
import com.umc.workbook.week2.domain.repository.RemoteUserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val service: UserService
) : RemoteUserRepository {

    override suspend fun getUser(apiKey: String): Result<UserData> = try {
        val response = service.getUser(1, apiKey)
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.data == null) Result.failure(RuntimeException("Response body is null"))
            else Result.success(body.data)
        } else {
            val errMsg = response.errorBody()?.string() ?: response.message()
            Result.failure(RuntimeException("HTTP ${response.code()}: $errMsg"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getUserList(apiKey: String): Result<List<UserData>> = try {
        val response = service.getUserList(apiKey)
        if (response.isSuccessful) {
            val body = response.body()
            if (body?.data == null) Result.failure(RuntimeException("Response body is null"))
            else Result.success(body.data)
        } else {
            val errMsg = response.errorBody()?.string() ?: response.message()
            Result.failure(RuntimeException("HTTP ${response.code()}: $errMsg"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}