package com.omid.filimo.fragments.forgetPassword

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
import com.omid.filimo.databinding.FragmentForgetPasswordBinding

class ForgetPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgetPasswordBinding
    private lateinit var owner: LifecycleOwner
    private lateinit var forgetPasswordViewModel: ForgetPasswordViewModel

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
        binding = FragmentForgetPasswordBinding.inflate(layoutInflater)
        forgetPasswordViewModel = ViewModelProvider(this)[ForgetPasswordViewModel::class.java]
   }

    private fun networkAvailable() {
        binding.apply {
            if (forgetPasswordViewModel.checkNetworkAvailable()) {
                clForgetPassword.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
            } else {
                clForgetPassword.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun checkLiveInternet(){
        binding.apply {
            forgetPasswordViewModel.checkNetworkConnection.observe(owner){ isConnect->
                if (isConnect){
                    clForgetPassword.visibility = View.VISIBLE
                    liveNoConnection.visibility = View.GONE
                }else {
                    clForgetPassword.visibility = View.GONE
                    liveNoConnection.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun forgetPasswordObserver(email: String) {
        forgetPasswordViewModel.getForgetPassword(email).observe(owner) { forgetPasswordModel->
            for (i in forgetPasswordModel.forgetPassword){
                if (i.success.contains("1")){
                    Toast.makeText(requireContext(),"درخواست شما به ایمیلتان ارسال شد", Toast.LENGTH_LONG).show()
                } else if (i.success.contains("0")) {
                    Toast.makeText(requireContext(),"چنین ایمیلی یافت نشد", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun clickEvents() {
        binding.apply {

            btnSendRequest.setOnClickListener {
                if (email.length() == 0){
                    email.error = "این فیلد نباید خالی باشد"
                }
                if (email.length() != 0){
                    forgetPasswordObserver(email.text.toString())
                }
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