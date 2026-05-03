package ddwu.com.mobile.week02

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ddwu.com.mobile.week02.remote.RemoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {
    private val _user = MutableLiveData<UserData>()
    val user: LiveData<UserData> = _user

    private val _userList = MutableLiveData<List<UserData>>()
    val userList: LiveData<List<UserData>> = _userList

    fun fetchUser(id:Int){
        viewModelScope.launch {
            try {
                val response = remoteRepository.getUser(id)
                _user.value = response.data
            } catch(e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun fetchUserList(){
        viewModelScope.launch {
            try{
                val response = remoteRepository.getUserList()
                _userList.value = response.data
            } catch(e:Exception){
                e.printStackTrace()
            }
        }
    }

}