package com.omid.filimo.fragments.offlineGalleryFragment

import android.content.ContentUris
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.omid.filimo.databinding.FragmentOfflineGalleryBinding
import java.io.File

class OfflineGalleryFragment : Fragment() {

    private lateinit var binding: FragmentOfflineGalleryBinding
    private lateinit var offlineGalleryViewModel: OfflineGalleryViewModel
    private val videoList = ArrayList<File>()
    private val videoListQ = ArrayList<Uri>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadVideos()
        setupRv()
    }

    private fun setupBinding(){
        binding = FragmentOfflineGalleryBinding.inflate(layoutInflater)
        offlineGalleryViewModel = ViewModelProvider(requireActivity())[OfflineGalleryViewModel::class.java]
    }

    private fun loadVideos(){
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q){
            val resolver = requireContext().contentResolver
            val videosUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            val projection = arrayOf(MediaStore.Video.Media._ID, MediaStore.Video.Media.DISPLAY_NAME)
            val selection = "${MediaStore.Video.Media.DISPLAY_NAME} LIKE ?"
            val selectionArgs = arrayOf("%.mp4")
            val cursor = resolver.query(videosUri, projection, selection, selectionArgs, null)
            cursor?.use {
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val contentUri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id)
                    videoListQ.add(contentUri)
                }
            }
        } else {
            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES).toString() + "/Filimo"
            val directory = File(path)
            val files = directory.listFiles()
            if (files != null) {
                for (file in files) {
                    if (file.name.endsWith(".mp4")) {
                        videoList.add(file)
                    }
                }
            }
        }
    }

    private fun setupRv(){
        binding.apply {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q){
                rvOffline.visibility = View.GONE
                rvOfflineQ.visibility = View.VISIBLE
                rvOfflineQ.adapter = OfflineGalleryAdapterQ(videoListQ)
                rvOfflineQ.layoutManager = GridLayoutManager(requireContext(),3)
            }else {
                rvOffline.visibility = View.VISIBLE
                rvOfflineQ.visibility = View.GONE
                rvOffline.adapter = OfflineGalleryAdapter(videoList)
                rvOffline.layoutManager = GridLayoutManager(requireContext(),3)
            }
        }
    }
}