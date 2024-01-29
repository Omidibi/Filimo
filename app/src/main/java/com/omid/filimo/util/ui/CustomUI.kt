package com.omid.filimo.util.ui

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.omid.filimo.R
import com.omid.filimo.databinding.ActivityMainBinding
import com.omid.filimo.util.configuration.AppConfiguration

class CustomUI {
    companion object{
        fun bnvDefaultView(binding : ActivityMainBinding){
            binding.apply {
                val defaultView = bottomNavigationView.findViewById<View>(R.id.item_showcase)
                val defaultAnimation = ObjectAnimator.ofFloat(defaultView, "translationY", -15f)
                defaultAnimation.duration = 200
                defaultAnimation.start()

                for (i in 0 until bottomNavigationView.menu.size()) {
                    if (bottomNavigationView.menu.getItem(i).itemId != R.id.item_showcase) {
                        val unselectedView = bottomNavigationView.findViewById<View>(bottomNavigationView.menu.getItem(i).itemId)
                        unselectedView?.clearAnimation()
                        val unselectedAnimation = ObjectAnimator.ofFloat(unselectedView, "translationY", 10f)
                        unselectedAnimation.duration = 200
                        unselectedAnimation.start()
                    }
                }
            }
        }

        fun bnvSetOnItemSelectListenerView(binding : ActivityMainBinding, it : MenuItem){
            binding.apply {
                val view = bottomNavigationView.findViewById<View>(it.itemId)
                view?.clearAnimation()
                val animation = ObjectAnimator.ofFloat(view, "translationY", -15f)
                animation.duration = 200
                animation.start()

                for (i in 0 until bottomNavigationView.menu.size()) {
                    if (bottomNavigationView.menu.getItem(i).itemId != it.itemId) {
                        val unselectedView = bottomNavigationView.findViewById<View>(bottomNavigationView.menu.getItem(i).itemId)
                        unselectedView?.clearAnimation()
                        val unselectedAnimation = ObjectAnimator.ofFloat(unselectedView, "translationY", 10f)
                        unselectedAnimation.duration = 200
                        unselectedAnimation.start()
                    }
                }
            }
        }

        fun bnvCustomView(binding: ActivityMainBinding){
            binding.apply {
                val iconTintList = ColorStateList(arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked)),
                    intArrayOf(ContextCompat.getColor(AppConfiguration.getContext(),R.color.gray), ContextCompat.getColor(AppConfiguration.getContext(),R.color.sunGlow)))
                bottomNavigationView.itemIconTintList = iconTintList


                val textColor = ColorStateList(arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked)),
                    intArrayOf(Color.parseColor("#00000000"), ContextCompat.getColor(AppConfiguration.getContext(),R.color.sunGlow)))
                bottomNavigationView.itemTextColor = textColor
            }
        }
    }
}