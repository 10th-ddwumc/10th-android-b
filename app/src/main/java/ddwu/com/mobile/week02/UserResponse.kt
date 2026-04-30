package ddwu.com.mobile.week02

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("data")
    val data: UserData
)

data class UserData (
    // id firstname lastname avatar
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String
)

data class UserDataList(
    @SerializedName("data")
    val data: List<UserData>
)