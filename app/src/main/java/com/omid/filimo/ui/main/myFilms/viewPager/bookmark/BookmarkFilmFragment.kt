package com.omid.filimo.ui.main.myFilms.viewPager.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omid.filimo.databinding.FragmentBookmarkFilmBinding

class BookmarkFilmFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkFilmBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()

        return binding.root
    }

    private fun setupBinding(){
        binding = FragmentBookmarkFilmBinding.inflate(layoutInflater)
        binding.apply {

        }
    }
}