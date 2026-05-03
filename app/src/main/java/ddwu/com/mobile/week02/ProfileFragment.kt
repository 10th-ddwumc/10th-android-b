package ddwu.com.mobile.week02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ddwu.com.mobile.week02.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import kotlin.collections.toMutableList
import androidx.fragment.app.viewModels

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUser(1)
        viewModel.fetchUserList()

        viewModel.user.observe(viewLifecycleOwner) {user ->
            binding.tvNickname.text = "${user.firstName} ${user.lastName}"

            Glide.with(requireContext())
                .load(user.avatar)
                .into(binding.ivProfile)
        }

        viewModel.userList.observe(viewLifecycleOwner){userList ->
            val adapter = FollowingAdapter(userList.toMutableList())
            binding.rvFollowing.adapter = adapter
            binding.rvFollowing.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL,false
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}