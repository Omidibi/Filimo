package com.omid.filimo.fragments.videoPlayerFragment

import android.Manifest
import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
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
import com.omid.filimo.model.VideoBookmark
import com.omid.filimo.ui.customView.customUI.Dialogs
import com.omid.filimo.utils.progressBarStatus.ProgressBarStatus
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import java.net.URL

class VideoPlayerFragment : Fragment(), IOnSelectListener {

    private lateinit var binding: FragmentVideoPlayerBinding
    private lateinit var videoPlayerViewModel: VideoPlayerViewModel
    private lateinit var owner: LifecycleOwner
    private lateinit var video : Video
    private lateinit var relatedList: MutableList<Related>
    private lateinit var userComment: MutableList<UserComment>
    private val appSettings = AppSettings()
    private lateinit var exoPlayer: ExoPlayer
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private var storagePermissions33 = arrayOf(Manifest.permission.READ_MEDIA_AUDIO)
    private var storagePermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        getData()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNetwork()
        checkUiStatus()
        check()
        singleVideoObserver()
        clickEvents()
        progressBarStatus()
    }

    override fun onStop() {
        super.onStop()
        exoPlayer.stop()
    }

    private fun setupBinding(){
        binding = FragmentVideoPlayerBinding.inflate(layoutInflater)
        videoPlayerViewModel = ViewModelProvider(this)[VideoPlayerViewModel::class.java]
        exoPlayer = ExoPlayer.Builder(requireContext()).build()
        relatedList = mutableListOf()
        userComment = mutableListOf()
    }

    private fun check(){
        binding.apply {
            if (appSettings.getLock() == 0){
                btnPlayOrRegister.text = getString(R.string.login_or_register)
            }else {
                btnPlayOrRegister.text = getString(R.string.play)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                textAbout.text = Html.fromHtml(video.videoDescription,Html.FROM_HTML_MODE_COMPACT)
            }else {
                textAbout.text = Html.fromHtml(video.videoDescription)
            }
            if (videoPlayerViewModel.isBookmarkEmpty(video.id)){
                Glide.with(requireContext()).load(R.drawable.not_bookmark).into(imgBookmark)
            }else {
                Glide.with(requireContext()).load(R.drawable.bookmark).into(imgBookmark)
            }
        }
    }

    private fun getData(){
        binding.apply {
            video = if (appSettings.getStatusFragment() == 1){
                Video(appSettings.getRelated().catId,"","",
                    appSettings.getRelated().categoryName,"",appSettings.getRelated().id,appSettings.getRelated().rateAvg,
                    appSettings.getRelated().totalViewer,appSettings.getRelated().videoDescription,appSettings.getRelated().videoDuration,
                    appSettings.getRelated().videoId,appSettings.getRelated().videoThumbnailB,appSettings.getRelated().videoThumbnailS,
                    appSettings.getRelated().videoTitle,appSettings.getRelated().videoType,appSettings.getRelated().videoUrl)
            }else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                    requireArguments().getParcelable("video",Video::class.java)!!
                }else {
                    requireArguments().getParcelable("video")!!
                }

            }
            Glide.with(requireContext()).load(video.videoThumbnailB).into(imgBanner)
            Glide.with(requireContext()).load(video.videoThumbnailB).into(imgVideo)
            videoName.text = video.videoTitle
            txtTotalViewer.text = video.totalViewer
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
                            if (clPlayer.visibility == View.VISIBLE){
                                clPlayer.visibility = View.VISIBLE
                                nsv.visibility = View.GONE
                            }else{
                                clPlayer.visibility = View.GONE
                                nsv.visibility = View.VISIBLE
                            }
                            pb.visibility = View.GONE
                            liveNoConnection.visibility = View.GONE
                            singleVideoModel.let {
                                for (singleVideo in it.singleVideo){
                                    for (comments in singleVideo.userComments){
                                        userComment.add(comments)

                                    }
                                    for(related in singleVideo.related){
                                        relatedList.add(related)
                                    }
                                    rvSingleVideo.adapter = it?.singleVideo?.let { SingleVideoAdapter(singleVideo.related,this@VideoPlayerFragment) }
                                    rvSingleVideo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                                    rvCommentList.adapter = it?.singleVideo?.let { ShowCommentAdapter(singleVideo.userComments) }
                                    rvCommentList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                                }
                            }
                            if (userComment.isEmpty()){
                                rvCommentList.visibility = View.GONE
                                txtNoComment.visibility = View.VISIBLE
                            }else {
                                rvCommentList.visibility = View.VISIBLE
                                txtNoComment.visibility = View.GONE
                            }

                        }
                    }else {
                        nsv.visibility = View.GONE
                        pb.visibility = View.GONE
                        liveNoConnection.visibility = View.VISIBLE
                        if (clPlayer.visibility == View.VISIBLE){
                            clPlayer.visibility = View.VISIBLE
                            exoPlayer.pause()
                        }
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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun clickEvents(){
        binding.apply {

            imgBack.setOnClickListener {
                findNavController().popBackStack()
                appSettings.saveStatusFragment(0)
                MainWidget.bnv.visibility = View.VISIBLE
                MainWidget.toolbar.visibility = View.VISIBLE
            }

            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
                if (clPlayer.visibility == View.VISIBLE){
                    clPlayer.visibility = View.GONE
                    nsv.visibility = View.VISIBLE
                    exoPlayer.stop()
                    exoPlayer.clearMediaItems()
                }else {
                    findNavController().popBackStack()
                    appSettings.saveStatusFragment(0)
                    MainWidget.bnv.visibility = View.VISIBLE
                    MainWidget.toolbar.visibility = View.VISIBLE
                }
            }

            sendComment.setOnClickListener {
                if (appSettings.getLock() == 0){
                    Dialogs.sendCommentDialog(requireContext(),this@VideoPlayerFragment)
                }else {
                    if (typeComment.text?.isEmpty() == true){
                        Toast.makeText(requireContext(),R.string.type_comment_to_send,Toast.LENGTH_LONG).show()
                    }else {
                        videoPlayerViewModel.getComment(typeComment.text.toString(),appSettings.getName().toString(),video.id).observe(owner){
                            typeComment.setText("")
                            Toast.makeText(requireContext(),R.string.send_comment_ok,Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

            btnPlayOrRegister.setOnClickListener{
                if (appSettings.getLock() == 0){
                    Dialogs.playOrRegisterDialog(requireContext(),this@VideoPlayerFragment)
                }else {
                    playVideo()
                }
            }

            imgClosePlayer.setOnClickListener {
                nsv.visibility = View.VISIBLE
                clPlayer.visibility = View.GONE
                exoPlayer.stop()
                exoPlayer.clearMediaItems()
            }

            imgBookmark.setOnClickListener {
                videoPlayerViewModel.insertBookmark(VideoBookmark(video.catId, video.categoryImage, video.categoryImageThumb, video.categoryName, video.cid, video.id, video.rateAvg, video.totalViewer, video.videoDescription, video.videoDuration, video.videoId, video.videoThumbnailB, video.videoThumbnailS, video.videoTitle, video.videoType, video.videoUrl),video.id,imgBookmark)
            }

            imgDownload.setOnClickListener {
                downloadVideo()
            }
        }
    }

    private fun playVideo(){
        binding.apply {
            nsv.visibility = View.GONE
            clPlayer.visibility = View.VISIBLE
            playingVideo.player = exoPlayer
            exoPlayer.addMediaItem(MediaItem.fromUri(video.videoUrl))
            exoPlayer.play()
            exoPlayer.prepare()
            videoPlayerViewModel.insertViewed(video,video.id)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun downloadVideo(){
        binding.apply {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(), storagePermissions33, 1)
                } else {
                    GlobalScope.launch(Dispatchers.IO) {
                        try {
                            val url = URL(video.videoUrl)
                            val connection = url.openConnection()
                            connection.connect()
                            val inputStream: InputStream = connection.getInputStream()
                            val resolver = requireContext().contentResolver
                            val contentValues = ContentValues().apply {
                                put(MediaStore.MediaColumns.DISPLAY_NAME, "${video.videoTitle}.mp4")
                                put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
                                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_MOVIES + "/Filimo")
                            }
                            val uri = resolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues)
                            val outputStream: OutputStream? = resolver.openOutputStream(uri!!)
                            inputStream.copyTo(outputStream!!)
                            outputStream.close()
                            withContext(Dispatchers.Main) {
                                Toast.makeText(requireContext(), getString(R.string.download_completed), Toast.LENGTH_LONG).show()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }

            } else {
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(), storagePermissions, 1)
                } else {
                    /** روش دانلود برای نسخه 10 اندروید*/
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
                        val request = DownloadManager.Request(Uri.parse(video.videoUrl))
                            .setTitle(requireActivity().title)
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            .setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES, "${video.videoTitle}.mp4")
                            .setAllowedOverMetered(true)
                            .setAllowedOverRoaming(true)
                        val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                        downloadManager.enqueue(request)
                    } else {
                        /** روش دانلود برای دیگر نسخه های اندروید*/
                        val request = DownloadManager.Request(Uri.parse(video.videoUrl))
                            .setTitle(requireActivity().title)
                            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                            .setDestinationInExternalPublicDir(Environment.DIRECTORY_MOVIES, "/Filimo/${video.videoTitle}.mp4")
                        val downloadManager = requireActivity().getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                        downloadManager.enqueue(request)
                    }
                }
            }
        }
    }

    private fun newStateFragment(video: Video){
        binding.apply {
            appSettings.saveStatusFragment(1)
            appSettings.saveRelate(Related(video.catId,video.categoryName,video.id,video.rateAvg,video.totalViewer,video.videoDescription,video.videoDuration,video.videoId,video.videoThumbnailB,video.videoThumbnailS,video.videoTitle,video.videoType,video.videoUrl))
            this@VideoPlayerFragment.video = Video(video.catId,video.categoryImage,video.categoryImageThumb,video.categoryName,video.cid,video.id,video.rateAvg,video.totalViewer,video.videoDescription,video.videoDuration,video.videoId,video.videoThumbnailB,video.videoThumbnailS,video.videoTitle,video.videoType,video.videoUrl)
            pb.visibility = View.VISIBLE
            nsv.visibility = View.GONE
            liveNoConnection.visibility = View.GONE
            videoPlayerViewModel.getSingleVideo(video.id).observe(owner){ singleVideoModel->
                relatedList.clear()
                userComment.clear()
                nsv.scrollTo(0,0)
                singleVideoModel.let {
                    for (singleVideo in it.singleVideo){
                        for (comments in singleVideo.userComments){
                            userComment.add(comments)
                        }
                        for(related in singleVideo.related){
                            relatedList.add(related)
                        }
                    }
                    rvSingleVideo.adapter = it?.singleVideo?.let { SingleVideoAdapter(relatedList,this@VideoPlayerFragment) }
                    rvSingleVideo.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
                    rvCommentList.adapter = it?.singleVideo?.let { ShowCommentAdapter(userComment) }
                    rvCommentList.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                }
                if (userComment.isEmpty()){
                    rvCommentList.visibility = View.GONE
                    txtNoComment.visibility = View.VISIBLE
                }else {
                    rvCommentList.visibility = View.VISIBLE
                    txtNoComment.visibility = View.GONE
                }
            }
            Glide.with(requireContext()).load(video.videoThumbnailB).into(imgBanner)
            Glide.with(requireContext()).load(video.videoThumbnailB).into(imgVideo)
            videoName.text = video.videoTitle
            txtTotalViewer.text = video.totalViewer
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                textAbout.text = Html.fromHtml(video.videoDescription,Html.FROM_HTML_MODE_COMPACT)
            }else {
                textAbout.text = Html.fromHtml(video.videoDescription)
            }
            if (videoPlayerViewModel.isBookmarkEmpty(video.id)){
                Glide.with(requireContext()).load(R.drawable.not_bookmark).into(imgBookmark)
            }else {
                Glide.with(requireContext()).load(R.drawable.bookmark).into(imgBookmark)
            }
        }
    }

    override fun onSelect(video: Video) {
        newStateFragment(video)
    }
}