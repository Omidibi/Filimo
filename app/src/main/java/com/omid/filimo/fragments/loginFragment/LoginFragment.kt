package com.omid.filimo.fragments.loginFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.omid.filimo.R
import com.omid.filimo.config.AppSettings
import com.omid.filimo.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val appSettings = AppSettings()
    private lateinit var owner: LifecycleOwner
    private lateinit var loginViewModel: LoginViewModel

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

    private fun setupBinding(){
        binding = FragmentLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun networkAvailable() {
        binding.apply {
            if (loginViewModel.checkNetworkAvailable()) {
                clLogin.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
            } else {
                clLogin.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun checkLiveInternet(){
        binding.apply {
            loginViewModel.checkNetworkConnection.observe(owner){ isConnect->
                if (isConnect){
                    clLogin.visibility = View.VISIBLE
                    liveNoConnection.visibility = View.GONE
                }else {
                    clLogin.visibility = View.GONE
                    liveNoConnection.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun registerObserver(name: String, email: String) {
        loginViewModel.getLogin(name, email).observe(owner) { userLoginModel->
            if (userLoginModel != null) {
                for (status in userLoginModel.userLogin) {
                    Toast.makeText(requireContext(), status.success, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun clickEvents(){
        binding.apply {
            imgClosePage.setOnClickListener {
                findNavController().popBackStack()
            }

            txtGoToRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_userRegisterFragment)
            }
        }
    }
}