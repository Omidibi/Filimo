package com.omid.filimo.ui.dashboard.myFilms

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.omid.filimo.fragments.bookmarkFragment.BookmarkFragment
import com.omid.filimo.fragments.offlineGalleryFragment.OfflineGalleryFragment
import com.omid.filimo.fragments.viewedFragment.ViewedFragment

class TabAdapter(private val totalTabs: Int, fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return totalTabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BookmarkFragment()
            1 -> ViewedFragment()
            2 -> OfflineGalleryFragment()
            else -> createFragment(position)
        }
    }

}