package com.omid.filimo.ui.main.showCase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.omid.filimo.api.WebServiceCaller
import com.omid.filimo.databinding.FragmentShowcaseBinding
import com.omid.filimo.model.listener.IListener
import com.omid.filimo.model.models.BannerModel
import com.omid.filimo.model.models.HomeVideos
import retrofit2.Call
import java.util.Timer
import java.util.TimerTask

class ShowCaseFragment : Fragment() {
    private lateinit var binding: FragmentShowcaseBinding
    private var currentPage = 0
    private val webServiceCaller = WebServiceCaller()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupBinding()
        banner()
        setupPagerBanner()

        return binding.root
    }

    private fun setupBinding(){
        binding = FragmentShowcaseBinding.inflate(layoutInflater)
        binding.apply {

        }
    }

    private fun setupPagerBanner(){
        binding.apply {
            val handler = Handler(Looper.getMainLooper())
            val update = Runnable {
                if (currentPage == (pagerBanner.adapter?.count ?: 0)){
                    currentPage = 0
                }
                pagerBanner.setCurrentItem(currentPage++,true)
            }

            Timer().schedule(object : TimerTask(){ override fun run() { handler.post(update) } },3000,3000)

            pagerBanner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    currentPage = position
                }

                override fun onPageScrollStateChanged(state: Int) {

                }

            })
        }
    }

    private fun banner() {
        binding.apply {
            webServiceCaller.getBanners(object : IListener<BannerModel>{
                override fun onSuccess(call: Call<BannerModel>, response: BannerModel) {
                    Log.e("", "")
                    pagerBanner.adapter = BannerAdapter(response.banner)
                }

                override fun onFailure(call: Call<BannerModel>, t: Throwable, errorResponse: String) {
                    Log.e("", "")
                }
            })
        }
    }

    private fun allVideos() {
        binding.apply {
            webServiceCaller.getHomeVideo(object : IListener<HomeVideos>{
                override fun onSuccess(call: Call<HomeVideos>, response: HomeVideos) {
                    Log.e("", "")
                }

                override fun onFailure(call: Call<HomeVideos>, t: Throwable, errorResponse: String) {
                    Log.e("", "")
                }

            })
        }
    }

    private fun featuredVideos() {
        binding.apply {
           webServiceCaller.getHomeVideo(object : IListener<HomeVideos>{
               override fun onSuccess(call: Call<HomeVideos>, response: HomeVideos) {
                   Log.e("", "")
               }

               override fun onFailure(call: Call<HomeVideos>, t: Throwable, errorResponse: String) {
                   Log.e("", "")
               }

           })
        }
    }

    private fun latestVideos() {
        binding.apply {
           webServiceCaller.getHomeVideo(object : IListener<HomeVideos>{
               override fun onSuccess(call: Call<HomeVideos>, response: HomeVideos) {
                   Log.e("", "")
               }

               override fun onFailure(call: Call<HomeVideos>, t: Throwable, errorResponse: String) {
                   Log.e("", "")
               }

           })
        }
    }

    /* private fun getBanners() {
        binding.apply {
            iService.getBanners().enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.e("", "")
                    val jsonObject = JSONObject(response.body()?.string())
                    val video = jsonObject.getString("ALL_IN_ONE_VIDEO")
                    val array = JSONArray(video)
                    val myList = mutableListOf<Banner>()
                    for (i in 0 until array.length()) {
                        val myObject = array.getJSONObject(i)
                        val id = myObject.getString("id")
                        val bannerName = myObject.getString("banner_name")
                        val bannerImage = myObject.getString("banner_image")
                        val bannerImageThumb = myObject.getString("banner_image_thumb")
                        val bannerUrl = myObject.getString("banner_url")
                        val banner = Banner(id, bannerName, bannerImage, bannerImageThumb, bannerUrl)
                        myList.add(banner)
                        pagerBanner.adapter = BannerAdapter(requireContext(),myList)
                        Log.e("", "")
                    }
                    Log.e("", "")
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("", "")
                }

            })
        }
    }*/

    /*private fun allVideo() {
      binding.apply {
          iService.getHomeData().enqueue(object : Callback<ResponseBody> {
              override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                  Log.e("", "")
                  val myObject = JSONObject(response.body()?.string())
                  val all = myObject.getString("ALL_IN_ONE_VIDEO")
                  val allObject = JSONObject(all)
                  val allVideo = allObject.getString("all_video")
                  val array = JSONArray(allVideo)
                  val allVideoList = mutableListOf<Video>()
                  for (i in 0 until array.length()) {
                      val obj = array.getJSONObject(i)
                      val id = obj.getString("id")
                      val catId = obj.getString("cat_id")
                      val videoType = obj.getString("video_type")
                      val videoTitle = obj.getString("video_title")
                      val videoUrl = obj.getString("video_url")
                      val videoId = obj.getString("video_id")
                      val videoThumbnailB = obj.getString("video_thumbnail_b")
                      val videoThumbnailS = obj.getString("video_thumbnail_s")
                      val videoDuration = obj.getString("video_duration")
                      val videoDescription = obj.getString("video_description")
                      val rateAvg = obj.getString("rate_avg")
                      val totalViewer = obj.getString("totel_viewer")
                      val cid = obj.getString("cid")
                      val categoryName = obj.getString("category_name")
                      val categoryImage = obj.getString("category_image")
                      val categoryImageThumb = obj.getString("category_image_thumb")
                      val video = Video(
                          id,
                          catId,
                          videoType,
                          videoTitle,
                          videoUrl,
                          videoId,
                          videoThumbnailB,
                          videoThumbnailS,
                          videoDuration,
                          videoDescription,
                          rateAvg,
                          totalViewer,
                          cid,
                          categoryName,
                          categoryImage,
                          categoryImageThumb
                      )
                      allVideoList.add(video)
                  }
                  Log.e("", "")
                  val adapter = VideosAdapter(requireContext(), allVideoList)
                  recyclerAllVideos.adapter = adapter

                  val manager = LinearLayoutManager(
                      requireContext(),
                      LinearLayoutManager.HORIZONTAL, false
                  )
                  recyclerAllVideos.layoutManager = manager

              }

              override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                  Log.e("", "")
              }
          })
      }
  }*/

    /*private fun featuredVideo() {
        binding.apply {
            iService.getHomeData().enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.e("", "")
                    progressbar.visibility = View.GONE
                    nestedScrollView.visibility = View.VISIBLE
                    val myObject = JSONObject(response.body()?.string())
                    val all = myObject.getString("ALL_IN_ONE_VIDEO")
                    val allObject = JSONObject(all)
                    val featured = allObject.getString("featured_video")
                    val array = JSONArray(featured)
                    val featuredList = mutableListOf<Video>()
                    for (i in 0 until array.length()) {
                        val obj = array.getJSONObject(i)
                        val id = obj.getString("id")
                        val catId = obj.getString("cat_id")
                        val videoType = obj.getString("video_type")
                        val videoTitle = obj.getString("video_title")
                        val videoUrl = obj.getString("video_url")
                        val videoId = obj.getString("video_id")
                        val videoThumbnailB = obj.getString("video_thumbnail_b")
                        val videoThumbnailS = obj.getString("video_thumbnail_s")
                        val videoDuration = obj.getString("video_duration")
                        val videoDescription = obj.getString("video_description")
                        val rateAvg = obj.getString("rate_avg")
                        val totalViewer = obj.getString("totel_viewer")
                        val cid = obj.getString("cid")
                        val categoryName = obj.getString("category_name")
                        val categoryImage = obj.getString("category_image")
                        val categoryImageThumb = obj.getString("category_image_thumb")
                        val video = Video(
                            id,
                            catId,
                            videoType,
                            videoTitle,
                            videoUrl,
                            videoId,
                            videoThumbnailB,
                            videoThumbnailS,
                            videoDuration,
                            videoDescription,
                            rateAvg,
                            totalViewer,
                            cid,
                            categoryName,
                            categoryImage,
                            categoryImageThumb
                        )
                        featuredList.add(video)
                    }
                    val adapter = VideosAdapter(requireContext(), featuredList)
                    recyclerFeaturedVideos.adapter = adapter

                    val manager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    recyclerFeaturedVideos.layoutManager = manager

                    Log.e("", "")
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("", "")
                    progressbar.visibility = View.GONE
                    nestedScrollView.visibility = View.VISIBLE
                }

            })
        }
    }*/

    /*private fun latestVideo() {
        binding.apply {
            iService.getHomeData().enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.e("", "")
                    val myObject = JSONObject(response.body()?.string())
                    val all = myObject.getString("ALL_IN_ONE_VIDEO")
                    val allObject = JSONObject(all)
                    val latestVideo = allObject.getString("latest_video")
                    val array = JSONArray(latestVideo)
                    val latestList = mutableListOf<Video>()
                    for (i in 0 until array.length()) {
                        val obj = array.getJSONObject(i)
                        val id = obj.getString("id")
                        val catId = obj.getString("cat_id")
                        val videoType = obj.getString("video_type")
                        val videoTitle = obj.getString("video_title")
                        val videoUrl = obj.getString("video_url")
                        val videoId = obj.getString("video_id")
                        val videoThumbnailB = obj.getString("video_thumbnail_b")
                        val videoThumbnailS = obj.getString("video_thumbnail_s")
                        val videoDuration = obj.getString("video_duration")
                        val videoDescription = obj.getString("video_description")
                        val rateAvg = obj.getString("rate_avg")
                        val totalViewer = obj.getString("totel_viewer")
                        val cid = obj.getString("cid")
                        val categoryName = obj.getString("category_name")
                        val categoryImage = obj.getString("category_image")
                        val categoryImageThumb = obj.getString("category_image_thumb")
                        val video = Video(
                            id,
                            catId,
                            videoType,
                            videoTitle,
                            videoUrl,
                            videoId,
                            videoThumbnailB,
                            videoThumbnailS,
                            videoDuration,
                            videoDescription,
                            rateAvg,
                            totalViewer,
                            cid,
                            categoryName,
                            categoryImage,
                            categoryImageThumb
                        )
                        latestList.add(video)
                    }
                    val adapter = VideosAdapter(requireContext(), latestList)
                    recyclerLatestVideos.adapter = adapter

                    val manager = LinearLayoutManager(
                        requireContext(),
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    recyclerLatestVideos.layoutManager = manager

                    Log.e("", "")
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.e("", "")
                }

            })
        }
    }*/

}