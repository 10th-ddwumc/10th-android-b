package ddwu.com.mobile.week02

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ReqResApi {
    @Headers("x-api-key: reqres_647c2e70f3704855891df2eaa4239957")
    @GET("users/{id}")
    suspend fun getUser(@Path("id")id: Int): UserResponse

    @Headers("x-api-key: reqres_647c2e70f3704855891df2eaa4239957")
    @GET("users")
    suspend fun getUserList(): UserDataList
}