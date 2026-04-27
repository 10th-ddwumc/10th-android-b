package com.example.nike.Profit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ReqresApi {

    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): UserResponse

    @GET("api/users")
    suspend fun getUserList(): UserListResponse
}