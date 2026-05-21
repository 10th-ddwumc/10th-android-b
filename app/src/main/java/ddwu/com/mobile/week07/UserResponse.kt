package ddwu.com.mobile.week07

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data")
    val data: UserData
)

data class UserData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName:String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String
)

data class UserListResponse(
    @SerializedName("data")
    val data: List<UserData>
)