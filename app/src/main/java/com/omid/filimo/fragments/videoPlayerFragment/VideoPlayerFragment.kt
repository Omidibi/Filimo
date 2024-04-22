package com.omid.filimo.fragments.videoPlayerFragment

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.omid.filimo.model.UserComment
import com.omid.filimo.model.Video
import com.omid.filimo.utils.progressBarStatus.ProgressBarStatus

class VideoPlayerFragment : Fragment(), IOnSelectListener {

    private lateinit var binding: FragmentVideoPlayerBinding
    private lateinit var videoPlayerViewModel: VideoPlayerViewModel
    private lateinit var owner: LifecycleOwner
    private lateinit var video : Video
    private lateinit var relatedList: MutableList<Related>
    private lateinit var userComment: MutableList<UserComment>
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
        checkUiStatus()
        check()
        singleVideoObserver()
        clickEvents()
        progressBarStatus()
    }

    private fun setupBinding(){
        binding = FragmentVideoPlayerBinding.inflate(layoutInflater)
        videoPlayerViewModel = ViewModelProvider(this)[VideoPlayerViewModel::class.java]
        relatedList = mutableListOf()
        userComment = mutableListOf()
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
                            for (singleVideo in singleVideoModel.singleVideo){
                                for (comments in singleVideo.userComments){
                                    userComment.add(comments)
                                }
                                for(related in singleVideo.related){
                                    relatedList.add(related)
                                }
                            }
                            if (userComment.isEmpty()){
                                rvCommentList.visibility = View.GONE
                                txtNoComment.visibility = View.VISIBLE
                            }else {
                                rvCommentList.visibility = View.VISIBLE
                                txtNoComment.visibility = View.GONE
                            }
                            rvSingleVideo.adapter = singleVideoModel?.singleVideo?.let { SingleVideoAdapter(relatedList,this@VideoPlayerFragment) }
                            rvSingleVideo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                            rvCommentList.adapter = singleVideoModel?.singleVideo?.let { ShowCommentAdapter(userComment) }
                            rvCommentList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
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

    private fun checkUiStatus(){
        if (MainWidget.bnv.visibility == View.GONE){
            MainWidget.bnv.visibility = View.GONE
        }
        if (MainWidget.toolbar.visibility == View.GONE){
            MainWidget.toolbar.visibility = View.GONE
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

            sendIt.setOnClickListener {
                if (appSettings.getLogin() == 0){
                    val dialog = AlertDialog.Builder(requireContext())
                    dialog.setTitle("ثبت نام یا ورود")
                    dialog.setMessage("برای ارسال نظر ابتدا وارد شوید یا ثبت نام کنید")
                    dialog.setPositiveButton("بله") { p0, p1 ->
                        findNavController().navigate(R.id.action_videoPlayerFragment_to_loginFragment)
                        MainWidget.bnv.visibility = View.GONE
                        MainWidget.toolbar.visibility = View.GONE
                    }
                    dialog.setNegativeButton("خیر") { p0, p1 ->

                    }
                    dialog.show()
                }else {
                    videoPlayerViewModel.getComment(typeComment.text.toString(),"").observe(owner){ commentModel->
                        for(i in commentModel.comment){
                            Toast.makeText(requireContext(),i.success,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            btnPlayOrRegister.setOnClickListener{
                if (appSettings.getLogin() == 0){
                    Toast.makeText(requireContext(),"ابتدا ثبت نام کنید یا به اکانت خود وارد شوید",Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(requireContext(),"ویدیو پخش شود",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun newStateFragment(video: Video){
        binding.apply {
            videoPlayerViewModel.getSingleVideo(video.id).observe(owner){ singleVideoModel->
                nsv.visibility = View.VISIBLE
                pb.visibility = View.GONE
                liveNoConnection.visibility = View.GONE
                nsv.scrollTo(0,0)
                relatedList.clear()
                userComment.clear()
                for (singleVideo in singleVideoModel.singleVideo){
                    for (comments in singleVideo.userComments){
                        userComment.add(comments)
                    }
                    for(related in singleVideo.related){
                        relatedList.add(related)
                    }
                }
                if (userComment.isEmpty()){
                    rvCommentList.visibility = View.GONE
                    txtNoComment.visibility = View.VISIBLE
                }else {
                    rvCommentList.visibility = View.VISIBLE
                    txtNoComment.visibility = View.GONE
                }
                rvSingleVideo.adapter = singleVideoModel?.singleVideo?.let { SingleVideoAdapter(relatedList,this@VideoPlayerFragment) }
                rvSingleVideo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                rvCommentList.adapter = singleVideoModel?.singleVideo?.let { ShowCommentAdapter(userComment) }
                rvCommentList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
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

    override fun onSelect(video: Video) {
        newStateFragment(video)
    }
}