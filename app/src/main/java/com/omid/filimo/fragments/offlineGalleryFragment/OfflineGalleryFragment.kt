package com.omid.filimo.fragments.offlineGalleryFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omid.filimo.databinding.FragmentOfflineGalleryBinding

class OfflineGalleryFragment : Fragment() {
    private lateinit var binding: FragmentOfflineGalleryBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()

        return binding.root
    }

    private fun setupBinding(){
        binding = FragmentOfflineGalleryBinding.inflate(layoutInflater)
        binding.apply {

        }
    }

}