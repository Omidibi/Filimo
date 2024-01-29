package com.omid.filimo.ui.main.myFilms

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.omid.filimo.ui.main.myFilms.viewPager.bookmark.BookmarkFilmFragment
import com.omid.filimo.ui.main.myFilms.viewPager.offlineGallery.OfflineGalleryFragment
import com.omid.filimo.ui.main.myFilms.viewPager.viewed.ViewedFragment

class TabAdapter(private val totalTabs: Int, fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BookmarkFilmFragment()
            1 -> ViewedFragment()
            2 -> OfflineGalleryFragment()
            else -> createFragment(position)
        }
    }

}