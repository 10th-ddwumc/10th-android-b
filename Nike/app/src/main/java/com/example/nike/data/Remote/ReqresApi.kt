package com.example.nike.data.Remote

import com.example.nike.Profit.UserListResponse
import com.example.nike.Profit.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ReqresApi {

    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): UserResponse

    @GET("api/users")
    suspend fun getUserList(): UserListResponse
}