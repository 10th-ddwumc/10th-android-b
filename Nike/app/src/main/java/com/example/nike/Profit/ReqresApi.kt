package com.example.nike.Profit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ReqresApi {
    @Headers("x-api-key: reqres_b203b1e826a74632b730a03c9f07d6d5")
    @GET("api/users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Call<UserResponse>

    @Headers("x-api-key: reqres_b203b1e826a74632b730a03c9f07d6d5")
    @GET("api/users")
    fun getUserList(): Call<UserListResponse>
}