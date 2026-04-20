package com.umc.workbook.week2.network

import android.util.Log
import com.umc.workbook.week2.data.UserData

class UserRepository(private val service: UserService) {
    //단건 조회
    suspend fun getUser(apiKey: String): Result<UserData> = try {
        val response = service.getUser(1, apiKey)

        if (response.isSuccessful) {
            val body = response.body()
            if (body == null) {
                Log.d("tag", "Response body is null")
                Result.failure(RuntimeException("Response body is null"))
            } else if (body.data == null) {
                Log.d("tag", "Response OK but Data is null")
                Result.failure(RuntimeException("Response OK but Data is null"))
            } else {
                Log.d("tag", "OK")
                Result.success(body.data)
            }
        } else {
            val errMsg = response.errorBody()?.string() ?: response.message()
            Result.failure(RuntimeException("HTTP ${response.code()}: $errMsg"))
        }
    }catch (e: Exception){
        Result.failure(e)
    }

    // 리스트 조회
    suspend fun getUserList(apiKey: String): Result<List<UserData>> = try{
        val response = service.getUserList(apiKey)

        if (response.isSuccessful) {
            val body = response.body()
            if (body == null) {
                Log.d("tag", "Response body is null")
                Result.failure(RuntimeException("Response body is null"))
            } else if (body.data == null) {
                Log.d("tag", "Response OK but Data is null")
                Result.failure(RuntimeException("Response OK but Data is null"))
            } else {
                Log.d("tag", "OK")
                Result.success(body.data)
            }
        } else {
            val errMsg = response.errorBody()?.string() ?: response.message()
            Result.failure(RuntimeException("HTTP ${response.code()}: $errMsg"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}