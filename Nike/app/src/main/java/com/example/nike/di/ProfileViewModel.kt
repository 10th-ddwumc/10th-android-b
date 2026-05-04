package com.example.nike.View.Ui.Base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nike.Profit.UserData
import com.example.nike.data.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfitViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _profile = MutableStateFlow<UserData?>(null)
    val profile = _profile.asStateFlow()

    private val _followingList = MutableStateFlow<List<UserData>>(emptyList())
    val followingList = _followingList.asStateFlow()

    fun loadMyProfile() {
        viewModelScope.launch {
            _profile.value = repository.getUser(1)
        }
    }

    fun loadFollowingList() {
        viewModelScope.launch {
            _followingList.value = repository.getUserList()
        }
    }
}