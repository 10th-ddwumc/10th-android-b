package com.example.nike.Screen.Ui.Data.repository

import com.example.nike.Screen.Ui.Data.UserData

class ProfileRepositoryImpl : ProfileRepository {

    override suspend fun getUser(id: Int): UserData {

        return UserData(
            id = 1,
            email = "nike@test.com",
            first_name = "Nike",
            last_name = "User",
            avatar = "https://reqres.in/img/faces/1-image.jpg"
        )
    }

    override suspend fun getUserList(): List<UserData> {

        return listOf(
            UserData(
                1,
                "1@test.com",
                "Emma",
                "Watson",
                "https://reqres.in/img/faces/1-image.jpg"
            ),

            UserData(
                2,
                "2@test.com",
                "Tom",
                "Hardy",
                "https://reqres.in/img/faces/2-image.jpg"
            ),

            UserData(
                3,
                "3@test.com",
                "Chris",
                "Evans",
                "https://reqres.in/img/faces/3-image.jpg"
            )
        )
    }
}