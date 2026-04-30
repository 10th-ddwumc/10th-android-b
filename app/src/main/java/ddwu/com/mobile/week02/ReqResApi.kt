package ddwu.com.mobile.week02

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ReqResApi {

    @GET("users/{id}")
    suspend fun getUser(@Path("id")id: Int): UserResponse

    @GET("users")
    suspend fun getUserList(): UserDataList
}