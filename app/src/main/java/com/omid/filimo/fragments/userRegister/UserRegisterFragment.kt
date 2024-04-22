package com.omid.filimo.fragments.userRegister

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omid.filimo.config.AppSettings
import com.omid.filimo.databinding.FragmentUserRegisterBinding

class UserRegisterFragment : Fragment() {

    private lateinit var binding: FragmentUserRegisterBinding
    private val appSettings = AppSettings()
    private lateinit var owner: LifecycleOwner
    private lateinit var userRegisterViewModel: UserRegisterViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        networkAvailable()
        checkLiveInternet()
        clickEvents()
    }

    private fun setupBinding() {
        binding = FragmentUserRegisterBinding.inflate(layoutInflater)
        userRegisterViewModel = ViewModelProvider(this)[UserRegisterViewModel::class.java]
    }

    private fun networkAvailable() {
        binding.apply {
            if (userRegisterViewModel.checkNetworkAvailable()) {
                clRegister.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
            } else {
                clRegister.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun checkLiveInternet(){
        binding.apply {
            userRegisterViewModel.checkNetworkConnection.observe(owner){ isConnect->
                if (isConnect){
                    clRegister.visibility = View.VISIBLE
                    liveNoConnection.visibility = View.GONE
                }else {
                    clRegister.visibility = View.GONE
                    liveNoConnection.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun registerObserver(name: String, email: String, password: String, phone: String) {
        userRegisterViewModel.getRegister(name, email, password, phone).observe(owner) { userRegisterModel ->
            if (userRegisterModel != null) {
                for (status in userRegisterModel.userRegister) {
                    Toast.makeText(requireContext(), status.success, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun clickEvents() {
        binding.apply {
            btnRegister.setOnClickListener {
                if (name.length() == 0){
                    name.error = "این فیلد نباید خالی باشد"
                }
                if (email.length() == 0){
                    email.error = "این فیلد نباید خالی باشد"
                }
                if (password.length() == 0){
                    password.error = "این فیلد نباید خالی باشد"
                }
                if (phone.length() == 0){
                    phone.error = "این فیلد نباید خالی باشد"
                }
                if (name.length() != 0 && email.length() != 0 && password.length() != 0 && phone.length() != 0){
                    registerObserver(name.text.toString(),email.text.toString(),password.text.toString(),phone.text.toString())
                    Toast.makeText(requireContext(),"شما ثبت نام کردید",Toast.LENGTH_LONG).show()
                }
            }

            login.setOnClickListener {
                findNavController().popBackStack()
            }

            imgClosePage.setOnClickListener {
                findNavController().popBackStack()
                findNavController().popBackStack()
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
                findNavController().popBackStack()
                findNavController().popBackStack()
            }
        }
    }
}