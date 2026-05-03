package ddwu.com.mobile.week02.remote

import ddwu.com.mobile.week02.RetrofitClient
import ddwu.com.mobile.week02.UserData
import ddwu.com.mobile.week02.UserDataList
import ddwu.com.mobile.week02.UserResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor() {
    suspend fun getUser(id:Int): UserResponse {
        return RetrofitClient.api.getUser(id)
    }
    suspend fun getUserList(): UserDataList{
        return RetrofitClient.api.getUserList()
    }
}