package com.umc.workbook.week2.network

import com.umc.workbook.week2.data.UserListResponse
import com.umc.workbook.week2.data.UserSingleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserService {
    @GET("api/users")
    suspend fun getUserList(
        @Header("x-api-key") apiKey: String
    ): Response<UserListResponse>

    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int,
        @Header("x-api-key") apiKey: String
    ): Response<UserSingleResponse>
}