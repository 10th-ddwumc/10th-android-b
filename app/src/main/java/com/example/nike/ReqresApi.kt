package com.example.nike

import com.example.nike.Screen.Ui.Data.UserListResponse
import com.example.nike.Screen.Ui.Data.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqresApi {

    @GET("api/users/{id}")
    suspend fun getUser(
        @Path("id") id: Int
    ): UserResponse

    @GET("api/users")
    suspend fun getUsers(
        @Query("page") page: Int = 1
    ): UserListResponse
}