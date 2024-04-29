package com.omid.filimo.fragments.videoPlayerOfflineFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.findNavController
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.databinding.FragmentVideoPlayerOfflineBinding

class VideoPlayerOfflineFragment : Fragment() {

    private lateinit var binding: FragmentVideoPlayerOfflineBinding
    private lateinit var exoPlayer: ExoPlayer
    private var uri = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        getData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPlayer()
        clickEvents()
    }

    override fun onPause() {
        super.onPause()
        exoPlayer.stop()
    }

    private fun setupBinding(){
        binding = FragmentVideoPlayerOfflineBinding.inflate(layoutInflater)
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
    }

    private fun getData(){
        uri = requireArguments().getString("play_offline")!!
    }

    private fun setupPlayer(){
        binding.apply {
            playingVideoOffline.player = exoPlayer
            exoPlayer.addMediaItem(MediaItem.fromUri(uri))
            exoPlayer.play()
            exoPlayer.prepare()
        }
    }

    private fun clickEvents(){
        binding.apply {
            imgBack.setOnClickListener {
                findNavController().popBackStack()
                MainWidget.bnv.visibility = View.VISIBLE
                MainWidget.toolbar.visibility = View.VISIBLE
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
                findNavController().popBackStack()
                MainWidget.bnv.visibility = View.VISIBLE
                MainWidget.toolbar.visibility = View.VISIBLE
            }
        }
    }
}