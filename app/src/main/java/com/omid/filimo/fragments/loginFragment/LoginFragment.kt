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

    override fun onStop() {
        super.onStop()
        binding.apply {
            password.setText("")
            email.setText("")
        }
    }

    private fun setupBinding() {
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

    private fun checkLiveInternet() {
        binding.apply {
            loginViewModel.checkNetworkConnection.observe(owner) { isConnect ->
                if (isConnect) {
                    clLogin.visibility = View.VISIBLE
                    liveNoConnection.visibility = View.GONE
                } else {
                    clLogin.visibility = View.GONE
                    liveNoConnection.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun userLoginObserver(email: String, password: String) {
        loginViewModel.getUserLogin(email, password).observe(owner) { userLoginModel ->
            for (i in userLoginModel.userLogin) {
                if (i.success.contains("1")) {
                    appSettings.lock(1)
                    appSettings.name(i.name)
                    appSettings.email(i.email)
                    appSettings.userId(i.userId)
                    Toast.makeText(requireContext(), getString(R.string.you_login), Toast.LENGTH_LONG).show()
                } else if (i.success.contains("0")) {
                    Toast.makeText(requireContext(), R.string.name_or_email_wrong, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun clickEvents() {
        binding.apply {

            btnLogin.setOnClickListener {
                if (email.length() == 0) {
                    email.error = getString(R.string.not_be_empty)
                }
                if (password.length() == 0) {
                    password.error = getString(R.string.not_be_empty)
                }
                if (email.length() != 0 && password.length() != 0) {
                    userLoginObserver(email.text.toString(), password.text.toString())
                }
            }

            imgClosePage.setOnClickListener {
                findNavController().popBackStack()
            }

            txtGoToRegister.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_userRegisterFragment)
            }

            txtForgetPassword.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
            }
        }
    }
}