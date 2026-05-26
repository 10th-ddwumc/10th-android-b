package com.umc.workbook.week2.ui.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.week2.data.UserData
import com.umc.workbook.week2.domain.repository.RemoteUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: RemoteUserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    init {
        fetchUser()
        fetchUserList()
    }

    private fun fetchUser() {
        viewModelScope.launch {
            repository.getUser()
                .onSuccess { user -> _uiState.update { it.copy(user = user) } }
                .onFailure { Log.d("ProfileViewModel", "유저 조회 실패: ${it.message}") }
        }
    }

    private fun fetchUserList() {
        viewModelScope.launch {
            repository.getUserList()
                .onSuccess { users -> _uiState.update { it.copy(userList = users) } }
                .onFailure { Log.d("ProfileViewModel", "리스트 조회 실패: ${it.message}") }
        }
    }
}

data class ProfileUiState(
    val user: UserData? = null,
    val userList: List<UserData> = emptyList()
)