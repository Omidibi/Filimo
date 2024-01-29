package com.omid.filimo.activities.mainActivity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.omid.filimo.R
import com.omid.filimo.databinding.ActivityMainBinding
import com.omid.filimo.ui.main.category.CategoryFragment
import com.omid.filimo.ui.main.myFilms.MyFilmsFragment
import com.omid.filimo.ui.main.showCase.ShowCaseFragment
import com.omid.filimo.util.ui.CustomUI

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentList: MutableList<Fragment>
    private val constraintSet = ConstraintSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        fragmentsStatusInActivity()
        setupBnvInActivity()
        setupViewPager2InActivity()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            setSupportActionBar(toolbar)
            CustomUI.bnvCustomView(binding)
            toolbar.setBackgroundColor(Color.TRANSPARENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                toolbar.elevation = 100f
            }
            constraintSet.clone(mainCl)
        }
    }

    private fun fragmentsStatusInActivity() {
        binding.apply {
            fragmentList = mutableListOf()
            fragmentList.add(MyFilmsFragment())
            fragmentList.add(CategoryFragment())
            fragmentList.add(ShowCaseFragment())
            binding.viewPager2.adapter = FragmentTabsAdapter(fragmentList, this@MainActivity).apply {
                    viewPager2.post {
                        viewPager2.setCurrentItem(2, false)
                        clShowCase.visibility = View.VISIBLE
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window.statusBarColor = Color.TRANSPARENT
                        }
                        constraintSet.connect(R.id.viewPager2, ConstraintSet.TOP, R.id.main_cl, ConstraintSet.TOP, 0)
                        constraintSet.applyTo(mainCl)
                        val params = toolbar.layoutParams as ViewGroup.MarginLayoutParams
                        params.topMargin = 65
                        toolbar.layoutParams = params
                        CustomUI.bnvDefaultView(binding)
                    }
            }
        }
    }

    private fun setupBnvInActivity() {
        binding.apply {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {

                    R.id.item_myFilms -> {
                        viewPager2.currentItem = 0
                        clShowCase.visibility = View.GONE
                        clCategoryMyFilms.visibility = View.VISIBLE
                        toolbar.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.nero_5))
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar.elevation = 0f
                            window.statusBarColor = ContextCompat.getColor(applicationContext,R.color.black)
                        }
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        constraintSet.connect(R.id.viewPager2, ConstraintSet.TOP, R.id.toolbar, ConstraintSet.BOTTOM, 0)
                        constraintSet.applyTo(mainCl)

                    }

                    R.id.item_category -> {
                        viewPager2.currentItem = 1
                        clShowCase.visibility = View.GONE
                        clCategoryMyFilms.visibility = View.VISIBLE
                        toolbar.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.nero_5))
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar.elevation = 0f
                            window.statusBarColor = ContextCompat.getColor(applicationContext,R.color.black)
                        }
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        constraintSet.connect(R.id.viewPager2, ConstraintSet.TOP, R.id.toolbar, ConstraintSet.BOTTOM, 0)
                        constraintSet.applyTo(mainCl)

                    }

                    R.id.item_showcase -> {
                        viewPager2.currentItem = 2
                        clShowCase.visibility = View.VISIBLE
                        clCategoryMyFilms.visibility = View.GONE
                        toolbar.setBackgroundColor(Color.TRANSPARENT)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            toolbar.elevation = 100f
                            window.statusBarColor = Color.TRANSPARENT
                        }
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        constraintSet.connect(R.id.viewPager2, ConstraintSet.TOP, R.id.main_cl, ConstraintSet.TOP, 0)
                        constraintSet.applyTo(mainCl)
                        val params = toolbar.layoutParams as ViewGroup.MarginLayoutParams
                        params.topMargin = 65
                        toolbar.layoutParams = params
                    }

                }

                CustomUI.bnvSetOnItemSelectListenerView(binding, it)

                return@setOnItemSelectedListener true
            }
        }
    }

    private fun setupViewPager2InActivity() {
        binding.apply {
            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> {
                            bottomNavigationView.menu.findItem(R.id.item_myFilms).isChecked = true
                        }

                        1 -> {
                            bottomNavigationView.menu.findItem(R.id.item_category).isChecked = true
                        }

                        2 -> {
                            bottomNavigationView.menu.findItem(R.id.item_showcase).isChecked = true
                        }
                    }
                }
            })

            viewPager2.isUserInputEnabled = false
        }
    }
}