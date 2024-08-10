package com.example.testchat.ui.profile.ui

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testchat.R
import com.example.testchat.databinding.FragmentEditBinding
import com.example.testchat.retrofit.model.RequestAvatar
import com.example.testchat.retrofit.model.RequestProfileData
import com.example.testchat.ui.profile.viewmodel.EditProfileState
import com.example.testchat.ui.profile.viewmodel.EditViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditViewModel by viewModels()

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                viewModel.processImage(it, requireContext().contentResolver)
            }
        }

    private val cameraLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap: Bitmap? ->
            bitmap?.let {
                viewModel.processBitmap(it)
            }
        }

    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 1001
        const val ARG_NAME = "arg_name"
        const val ARG_BIRTHDAY = "arg_birthday"
        const val ARG_CITY = "arg_city"
        const val ARG_VK = "arg_vk"
        const val ARG_INST = "arg_inst"
        const val ARG_STATUS = "arg_status"
        const val ARG_USER_NAME = "arg_user_name"

        fun newInstance(
            name: String,
            userName:String,
            birthday: String?,
            city: String,
            vk: String,
            inst: String,
            status: String
        ): EditFragment {
            val fragment = EditFragment()
            val args = Bundle().apply {
                putString(ARG_NAME, name)
                putString(ARG_BIRTHDAY, birthday)
                putString(ARG_CITY, city)
                putString(ARG_VK, vk)
                putString(ARG_INST, inst)
                putString(ARG_STATUS, status)
                putString(ARG_USER_NAME, userName)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edDate.addTextChangedListener(DateInputTextWatcher(binding.edDate))

        observeViewModel()

        getArg()

        binding.run {
            btnSaveProfile.setOnClickListener {
                val request = RequestProfileData(
                    edName.text.toString(),
                    edUserName.text.toString(),
                    edDate.text.toString(),
                    edCity.text.toString(),
                    edVk.text.toString(),
                    edInst.text.toString(),
                    edStatus.text.toString(),
                    RequestAvatar(
                        viewModel.imageData.value?.first,
                        viewModel.imageData.value?.second
                    ),
                )
                viewModel.editProfile(request)
            }
            btnUploadAvatar.setOnClickListener {
                checkCameraPermission()
            }
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            showImagePickerDialog()
        } else {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    private fun observeViewModel() {
        viewModel.editProfileState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is EditProfileState.Idle -> {
                    showIdleState()
                }

                is EditProfileState.Loading -> {
                    showLoadingState()
                }

                is EditProfileState.Success -> {
                    showSuccessState(state)
                }

                is EditProfileState.Error -> {
                    showErrorState(state.message)
                }
            }
        }
    }

    private fun showIdleState() {
        binding.run {
            progressBar.visibility = View.GONE
            txtError.visibility = View.GONE
            txtSuccess.visibility = View.GONE
            btnSaveProfile.text = requireContext().resources.getText(R.string.str_edit_profile)
        }
    }

    private fun showLoadingState() {
        binding.run {
            progressBar.visibility = View.VISIBLE
            txtError.visibility = View.GONE
            txtSuccess.visibility = View.GONE
            btnSaveProfile.text = ""
        }
    }

    private fun showSuccessState(state: EditProfileState.Success) {
        binding.run {
            progressBar.visibility = View.GONE
            txtError.visibility = View.GONE
            txtSuccess.visibility = View.VISIBLE
            btnSaveProfile.text = requireContext().resources.getText(R.string.str_edit_profile)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            val navController = findNavController()
            navController.previousBackStackEntry?.savedStateHandle?.set(
                "profileData",
                state.data
            )
            navController.popBackStack()
        }, 1000)
    }

    private fun showErrorState(message: String) {
        binding.run {
            progressBar.visibility = View.GONE
            txtError.visibility = View.VISIBLE
            txtSuccess.visibility = View.GONE
            txtError.text = message
            btnSaveProfile.text = requireContext().resources.getText(R.string.str_edit_profile)
        }

    }

    private fun showImagePickerDialog() {
        val options = arrayOf("Камера", "Галерея")
        val builder = android.app.AlertDialog.Builder(requireContext())
        builder.setTitle("Выберите источник")
        builder.setItems(options) { dialog, which ->
            when (which) {
                0 -> openCamera()
                1 -> openGallery()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        cameraLauncher.launch(null)
    }

    private fun openGallery() {
        galleryLauncher.launch("image/*")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getArg() {
        arguments?.let {
            val name = it.getString(ARG_NAME)
            val userName = it.getString(ARG_USER_NAME)
            val birthday = it.getString(ARG_BIRTHDAY)
            val city = it.getString(ARG_CITY)
            val vk = it.getString(ARG_VK)
            val inst = it.getString(ARG_INST)
            val status = it.getString(ARG_STATUS)

            Log.e("Args", "$name $userName $birthday $city  $vk $inst $status")

            binding.run {
                edName.setText(name)
                edUserName.setText(userName)
                edCity.setText(city)
                edDate.setText(birthday)
                edVk.setText(vk)
                edInst.setText(inst)
                edStatus.setText(status)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




