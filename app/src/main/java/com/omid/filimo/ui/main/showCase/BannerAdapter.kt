package com.omid.filimo.ui.main.showCase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.omid.filimo.R
import com.omid.filimo.model.models.Banner
import com.omid.filimo.util.configuration.AppConfiguration

class BannerAdapter(private var musicList: List<Banner>) : PagerAdapter() {
    override fun getCount(): Int {
        return musicList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(AppConfiguration.getContext()).inflate(R.layout.banner_row, null)
        val imgNews = view.findViewById<AppCompatImageView>(R.id.img_banner)
        container.addView(view, 0)
        val bannerImage = musicList[position]
        Glide.with(AppConfiguration.getContext()).load(bannerImage.bannerImage).into(imgNews)
        return view
    }
}