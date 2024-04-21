package com.omid.filimo.fragments.videoPlayerFragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.config.AppSettings
import com.omid.filimo.databinding.FragmentVideoPlayerBinding
import com.omid.filimo.model.Related
import com.omid.filimo.model.Video
import com.omid.filimo.utils.progressBarStatus.ProgressBarStatus

class VideoPlayerFragment : Fragment(), IOnSelectListener {

    private lateinit var binding: FragmentVideoPlayerBinding
    private lateinit var videoPlayerViewModel: VideoPlayerViewModel
    private lateinit var owner: LifecycleOwner
    private lateinit var video : Video
    private lateinit var relatedList: MutableList<Related>
    private val appSettings = AppSettings()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        getData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNetwork()
        check()
        singleVideoObserver()
        clickEvents()
        progressBarStatus()
    }

    private fun setupBinding(){
        binding = FragmentVideoPlayerBinding.inflate(layoutInflater)
        videoPlayerViewModel = ViewModelProvider(this)[VideoPlayerViewModel::class.java]
        relatedList = mutableListOf()
    }

    private fun check(){
        binding.apply {
            if (appSettings.getLogin() == 0){
                btnPlayOrRegister.text = getString(R.string.login_or_register)
            }else {
                btnPlayOrRegister.text = getString(R.string.play)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                textAbout.text = Html.fromHtml(video.videoDescription,Html.FROM_HTML_MODE_COMPACT)
            }else {
                textAbout.text = Html.fromHtml(video.videoDescription)
            }
        }
    }

    private fun getData(){
        binding.apply {
            video = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                requireArguments().getParcelable("video",Video::class.java)!!
            }else {
                requireArguments().getParcelable("video")!!
            }
            Log.e("","")
            Glide.with(requireContext()).load(video.videoThumbnailB).into(imgBanner)
            Glide.with(requireContext()).load(video.videoThumbnailB).into(imgVideo)
            videoName.text = video.videoTitle
            rateVideo.rating = video.rateAvg.toFloat()
        }
    }

    private fun progressBarStatus() {
        ProgressBarStatus.pbStatus(binding.pb)
    }

    private fun checkNetwork(){
        binding.apply {
            if (videoPlayerViewModel.networkAvailable()) {
                nsv.visibility = View.VISIBLE
                pb.visibility = View.GONE
                liveNoConnection.visibility = View.GONE
            } else {
                nsv.visibility = View.GONE
                pb.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun singleVideoObserver(){
        binding.apply {
            if (isAdded){
                videoPlayerViewModel.checkNetworkConnection.observe(owner){ isConnect->
                    nsv.visibility = View.GONE
                    pb.visibility = View.VISIBLE
                    liveNoConnection.visibility = View.GONE
                    if (isConnect){
                        videoPlayerViewModel.getSingleVideo(video.id).observe(owner){ singleVideoModel->
                            nsv.visibility = View.VISIBLE
                            pb.visibility = View.GONE
                            liveNoConnection.visibility = View.GONE
                            for (i in singleVideoModel.singleVideo){
                                for(j in i.related){
                                    relatedList.add(j)
                                }
                            }
                            rvSingleVideo.adapter = singleVideoModel?.singleVideo?.let { SingleVideoAdapter(relatedList,this@VideoPlayerFragment) }
                            rvSingleVideo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                        }
                    }else {
                        nsv.visibility = View.GONE
                        pb.visibility = View.GONE
                        liveNoConnection.visibility = View.VISIBLE
                    }
                }
            }
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

    override fun onSelect(video: Video) {
        binding.apply {
            videoPlayerViewModel.getSingleVideo(video.id).observe(owner){ singleVideoModel->
                nsv.visibility = View.VISIBLE
                pb.visibility = View.GONE
                liveNoConnection.visibility = View.GONE
                relatedList.clear()
                for (i in singleVideoModel.singleVideo){
                    for(j in i.related){
                        relatedList.add(j)
                    }
                }
                rvSingleVideo.adapter = singleVideoModel?.singleVideo?.let { SingleVideoAdapter(relatedList,this@VideoPlayerFragment) }
                rvSingleVideo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
            }
            Glide.with(requireContext()).load(video.videoThumbnailB).into(imgBanner)
            Glide.with(requireContext()).load(video.videoThumbnailB).into(imgVideo)
            videoName.text = video.videoTitle
            rateVideo.rating = video.rateAvg.toFloat()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                textAbout.text = Html.fromHtml(video.videoDescription,Html.FROM_HTML_MODE_COMPACT)
            }else {
                textAbout.text = Html.fromHtml(video.videoDescription)
            }
        }
    }
}