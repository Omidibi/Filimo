package com.omid.filimo.fragments.videoListByCatIdFragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.filimo.activity.MainWidget
import com.omid.filimo.databinding.FragmentVideoListByCatIdBinding
import com.omid.filimo.model.Category
import com.omid.filimo.model.Video
import com.omid.filimo.utils.ProgressBarStatus

class VideoListByCatIdFragment : Fragment() {

    private lateinit var binding: FragmentVideoListByCatIdBinding
    private lateinit var videoListByCatIdViewModel: VideoListByCatIdViewModel
    private lateinit var video : Category
    private lateinit var owner: LifecycleOwner

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
        videoByCatIdObserver()
        srlStatus()
        progressBarStatus()
        clickEvents()
    }

    private fun setupBinding(){
        binding = FragmentVideoListByCatIdBinding.inflate(layoutInflater)
        videoListByCatIdViewModel = ViewModelProvider(this)[VideoListByCatIdViewModel::class.java]
    }

    private fun getData(){
        binding.apply {
            video = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                requireArguments().getParcelable("category",Category::class.java)!!
            }else {
                requireArguments().getParcelable("category")!!
            }
            toolbarTitle.text = video.categoryName
        }
    }

    private fun progressBarStatus() {
        ProgressBarStatus.pbStatus(binding.pbCatById)
    }

    private fun checkNetwork(){
        binding.apply {
            if (videoListByCatIdViewModel.networkAvailable()) {
                srl.visibility = View.VISIBLE
                pbCatById.visibility = View.GONE
                liveNoConnection.visibility = View.GONE
            } else {
                srl.visibility = View.GONE
                pbCatById.visibility = View.GONE
                liveNoConnection.visibility = View.VISIBLE
            }
        }
    }

    private fun videoByCatIdObserver(){
        binding.apply {
            if (isAdded){
                videoListByCatIdViewModel.checkNetworkConnection.observe(owner){ isConnect->
                    pbCatById.visibility = View.VISIBLE
                    srl.visibility = View.GONE
                    liveNoConnection.visibility = View.GONE
                    if (isConnect){
                        videoListByCatIdViewModel.getVideoListByCatId(video.cid).observe(owner){ catListByIdModel->
                            pbCatById.visibility = View.GONE
                            srl.visibility = View.VISIBLE
                            liveNoConnection.visibility = View.GONE
                            rvCatById.adapter = catListByIdModel?.categoryList?.let { VideoListByCatIdAdapter(it) }
                            rvCatById.layoutManager = GridLayoutManager(requireContext(),3)
                        }
                    }else {
                        pbCatById.visibility = View.GONE
                        srl.visibility = View.GONE
                        liveNoConnection.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun srlStatus(){
        binding.apply {
            srl.setOnRefreshListener {
                pbCatById.visibility = View.VISIBLE
                liveNoConnection.visibility = View.GONE
                srl.visibility = View.GONE
                videoListByCatIdViewModel.getVideoListByCatId(video.cid)
                srl.isRefreshing = false
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
}