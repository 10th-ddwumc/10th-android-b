package ddwu.com.mobile.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ddwu.com.mobile.week02.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import kotlin.collections.toMutableList

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            try{
                val response = RetrofitClient.api.getUser(1)
                val user = response.data

                if (_binding == null) return@launch

                binding.tvNickname.text = "${user.firstName} ${user.lastName}"

                Glide.with(requireContext())
                    .load(user.avatar)
                    .into(binding.ivProfile)
            } catch (e: Exception){
                e.printStackTrace()
                android.util.Log.e("ProfileFragment", "에러: ${e.message}")
            }
        }

        lifecycleScope.launch {
            try{
                val response = RetrofitClient.api.getUserList()
                val userList = response.data.toMutableList()

                if (_binding == null) return@launch

                val adapter = FollowingAdapter(userList)
                binding.rvFollowing.adapter = adapter
                binding.rvFollowing.layoutManager = LinearLayoutManager(requireContext(),
                    LinearLayoutManager.HORIZONTAL,false)
            } catch(e: Exception){
                e.printStackTrace()
                android.util.Log.e("ProfileFragment", "에러: ${e.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}