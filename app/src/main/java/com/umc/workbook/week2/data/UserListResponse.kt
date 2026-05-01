package com.umc.workbook.week2.data

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: List<UserData>,
    val support: Support,
    @SerializedName("_meta")
    val meta: Meta
)

data class UserData(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)

data class Support(
    val url: String,
    val text: String
)

data class Meta(
    @SerializedName("powered_by")
    val poweredBy: String,
    @SerializedName("docs_url")
    val docsUrl: String,
    @SerializedName("upgrade_url")
    val upgradeUrl: String,
    @SerializedName("example_url")
    val exampleUrl: String,
    val variant: String,
    val message: String,
    val cta: Cta,
    val context: String
)

data class Cta(
    val label: String,
    val url: String
)

data class UserSingleResponse(
    val data: UserData,
    val support: Support,
    @SerializedName("_meta")
    val meta: Meta
)
