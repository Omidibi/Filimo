package com.omid.filimo.ui.main.myFilms.viewPager.viewed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omid.filimo.databinding.FragmentViewedBinding

class ViewedFragment : Fragment() {
    private lateinit var binding : FragmentViewedBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()

        return binding.root
    }

  private fun setupBinding(){
      binding = FragmentViewedBinding.inflate(layoutInflater)
      binding.apply {

      }
  }
}