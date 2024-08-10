package com.example.testchat.ui.profile.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testchat.R
import com.example.testchat.databinding.FragmentProfileBinding
import com.example.testchat.retrofit.model.RequestProfileData
import com.example.testchat.ui.profile.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val viewModel: ProfileViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        observeViewModel()

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<RequestProfileData?>(
            "profileData"
        )
            ?.observe(viewLifecycleOwner) { newProfileData ->
                Log.e("EditFragemnt","IsBack")
                viewModel.insertProfileData(
                    newProfileData?.name,
                    newProfileData?.username,
                    newProfileData?.birthday,
                    newProfileData?.city,
                    newProfileData?.vk,
                    newProfileData?.instagram,
                    newProfileData?.status,
                    null,
                    null,
                    null,
                    null,
                    binding.txtNumber.text.toString()
                )
            }




        binding.btnEditProfile.setOnClickListener {
            val bundle = Bundle().apply {
                putString(EditFragment.ARG_NAME, binding.txtName.text.toString())
                putString(EditFragment.ARG_CITY, binding.txtCity.text.toString())
                putString(EditFragment.ARG_BIRTHDAY, binding.txtBirthday.text.toString())
                putString(EditFragment.ARG_VK, binding.txtVk.text.toString())
                putString(EditFragment.ARG_INST, binding.txtInst.text.toString())
                putString(EditFragment.ARG_STATUS, binding.txtStatus.text.toString())
                putString(EditFragment.ARG_USER_NAME, binding.txtUserName.text.toString())
            }
            findNavController().navigate(R.id.action_profileFragment_to_editFragment, bundle)
        }


    }

    private fun observeViewModel() {
        viewModel.profileData.observe(viewLifecycleOwner) { profileData ->
            profileData?.let {
                binding.run {
                    txtCity.text = profileData.city
                    txtName.text = profileData.name
                    txtNumber.text = profileData.phone
                    txtBirthday.text = profileData.birthday
                    txtUserName.text = profileData.username
                    txtVk.text = profileData.vk
                    txtInst.text = profileData.instagram
                    txtStatus.text = profileData.status
                    txtZodiac.text = viewModel.getZodiacSign(profileData.birthday.toString())
                    Log.e("Avatar", profileData.avatar.toString())
                    Log.e("Avatar", profileData.avatars?.bigAvatar.toString())
                }

            }
        }
        viewModel.getProfileDataById()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
}