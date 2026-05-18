package com.example.nike.Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.RetrofitClient
import com.example.nike.Screen.Ui.Data.UserData
import com.example.nike.Screen.Ui.Data.repository.ProfileRepository
import com.example.nike.Screen.Ui.Data.repository.ProfileRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
class ProfitViewModel : ViewModel() {

    private val _profile = MutableStateFlow<UserData?>(null)
    val profile = _profile.asStateFlow()

    private val _followingList = MutableStateFlow<List<UserData>>(emptyList())
    val followingList = _followingList.asStateFlow()

    fun loadMyProfile() {

        viewModelScope.launch {

            try {

                val response = RetrofitClient.api.getUser(1)

                _profile.value = response.data

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadFollowingList() {

        viewModelScope.launch {

            try {

                val response = RetrofitClient.api.getUsers()

                _followingList.value = response.data.take(3)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}