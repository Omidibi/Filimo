package com.omid.filimo.fragments.userProfileFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omid.filimo.config.AppSettings
import com.omid.filimo.databinding.FragmentUserProfileBinding

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding
    private val appSettings = AppSettings()
    private lateinit var userProfileViewModel: UserProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkAvailable()
        userLoginObserver()
        checkLiveInternet()
        clickEvents()
    }

    private fun setupBinding(){
        binding = FragmentUserProfileBinding.inflate(layoutInflater)
        userProfileViewModel = ViewModelProvider(requireActivity())[UserProfileViewModel::class.java]
        binding.name.setText(appSettings.getName())
        binding.email.setText(appSettings.getEmail())
        binding.password.setText(appSettings.getPassword())
        binding.phone.setText(appSettings.getPhone())
    }

    private fun networkAvailable() {
        binding.apply {
            if (userProfileViewModel.checkNetworkAvailable()) {
                clProfile.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
            } else {
                clProfile.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun checkLiveInternet(){
        binding.apply {
            userProfileViewModel.checkNetworkConnection.observe(viewLifecycleOwner){ isConnect->
                if (isConnect){
                    clProfile.visibility = View.VISIBLE
                    liveNoConnection.visibility = View.GONE
                }else {
                    clProfile.visibility = View.GONE
                    liveNoConnection.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun profileUpdateObserver(userId: String, name: String, email: String, password: String, phone: String) {
        binding.apply {
            userProfileViewModel.getProfileUpdate(userId, name, email, password, phone).observe(viewLifecycleOwner) {
                appSettings.email(email)
                appSettings.password(password)
                appSettings.name(name)
                appSettings.phone(phone)
                Toast.makeText(requireContext(),"اطلاعات حساب کاربری شما بروز شد",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun userLoginObserver(){
        binding.apply {
            userProfileViewModel.getUserLogin(appSettings.getEmail().toString(), appSettings.getPassword().toString()).observe(viewLifecycleOwner){ userLoginModel->
                for (i in userLoginModel.userLogin){
                    if (i.success.contains("1")){
                        appSettings.lock(1)
                        appSettings.name(i.name)
                        appSettings.email(i.email)
                        appSettings.userId(i.userId)
                    } else if (i.success.contains("0")) {
                        Log.e("",i.success)
                    }
                }
            }
        }
    }

    private fun clickEvents(){
        binding.apply {
            btnEdite.setOnClickListener {
                if (name.text!!.isEmpty()){
                    name.error = "اسن فیلد نباید خالی باشد"
                }
                if (email.text!!.isEmpty()){
                    email.error = "اسن فیلد نباید خالی باشد"
                }
                if (password.text!!.isEmpty()){
                    password.error = "اسن فیلد نباید خالی باشد"
                }
                if (phone.text!!.isEmpty()){
                    phone.error = "اسن فیلد نباید خالی باشد"
                }
                if (name.length() != 0 && email.length() != 0 && password.length() != 0 && phone.length() != 0){
                    profileUpdateObserver(appSettings.getUserId().toString(),name.text.toString(),email.text.toString(),password.text.toString(),phone.text.toString())
                    appSettings.email(email.text.toString())
                    appSettings.password(password.text.toString())
                    appSettings.phone(phone.text.toString())
                    appSettings.name(name.text.toString())
                }
            }

            btnExit.setOnClickListener {
               appSettings.lock(0)
               findNavController().popBackStack()
               Toast.makeText(requireContext(),"شما از حساب خارج شدید",Toast.LENGTH_LONG).show()
            }

            imgClosePage.setOnClickListener {
                findNavController().popBackStack()
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
                findNavController().popBackStack()
            }
        }
    }
}