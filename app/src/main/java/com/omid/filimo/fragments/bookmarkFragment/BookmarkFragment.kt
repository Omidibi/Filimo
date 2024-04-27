package com.omid.filimo.fragments.bookmarkFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.filimo.databinding.FragmentBookmarkFilmBinding

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkFilmBinding
    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNetwork()
        checkLiveInternet()
        setupRvViewed()
    }

    private fun setupBinding(){
        binding = FragmentBookmarkFilmBinding.inflate(layoutInflater)
        bookmarkViewModel = ViewModelProvider(requireActivity())[BookmarkViewModel::class.java]
    }

    private fun checkNetwork(){
        binding.apply {
            if (bookmarkViewModel.networkAvailable()){
                rvBookmark.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
            }else {
                rvBookmark.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun checkLiveInternet(){
        binding.apply {
            bookmarkViewModel.checkNetworkConnection.observe(viewLifecycleOwner){ isConnected->
                if (isConnected){
                    rvBookmark.visibility = View.VISIBLE
                    liveNoConnection.visibility = View.GONE
                }else {
                    rvBookmark.visibility = View.GONE
                    liveNoConnection.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRvViewed(){
        binding.apply {
            rvBookmark.adapter = BookmarkAdapter(bookmarkViewModel.showAllBookmark(),this@BookmarkFragment)
            rvBookmark.layoutManager = GridLayoutManager(requireContext(),3)
        }
    }
}