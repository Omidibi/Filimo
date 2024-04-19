package com.omid.filimo.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.omid.filimo.R
import com.omid.filimo.databinding.ActivityMainBinding
import com.omid.filimo.ui.customView.customUI.CustomUI

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHost: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        currentPage()
        setupBnvInActivity()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            MainWidget.bnv = bnv
            MainWidget.toolbar = toolbar
            MainWidget.clShowCase = clShowCase
            MainWidget.clCategoryMyFilms = clCategoryMyFilms
            navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHost.navController
            NavigationUI.setupWithNavController(bnv, navController)
            CustomUI.bnvCustomView(binding)
            CustomUI.bnvDefaultView(binding)
        }
    }

    private fun currentPage(){
        binding.apply {
            navController.navigate(R.id.showCaseFragment)
            bnv.menu.findItem(R.id.item_showcase).isChecked = true
            clShowCase.visibility = View.VISIBLE
            clCategoryMyFilms.visibility = View.GONE
        }
    }

    private fun setupBnvInActivity() {
        binding.apply {
            bnv.setOnItemSelectedListener {
                when (it.itemId) {

                    R.id.item_myFilms -> {
                        navController.navigate(R.id.myFilmsFragment)
                        clShowCase.visibility = View.GONE
                        clCategoryMyFilms.visibility = View.VISIBLE
                    }

                    R.id.item_category -> {
                        navController.navigate(R.id.categoryFragment)
                        clShowCase.visibility = View.GONE
                        clCategoryMyFilms.visibility = View.VISIBLE
                    }

                    R.id.item_showcase -> {
                        navController.navigate(R.id.showCaseFragment)
                        clShowCase.visibility = View.VISIBLE
                        clCategoryMyFilms.visibility = View.GONE
                    }

                }
                CustomUI.bnvSetOnItemSelectListenerView(binding, it)
                return@setOnItemSelectedListener true
            }
        }
    }
}