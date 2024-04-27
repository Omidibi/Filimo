package com.omid.filimo.activity

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.omid.filimo.R
import com.omid.filimo.config.AppSettings
import com.omid.filimo.databinding.ActivityMainBinding
import com.omid.filimo.ui.customView.customUI.CustomUI

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHost: NavHostFragment
    private lateinit var navController: NavController
    private val appSettings = AppSettings()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        currentPage()
        setupBnvInActivity()
        clickEvent()
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)
            MainWidget.bnv = bnv
            MainWidget.toolbar = toolbar
            navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHost.navController
            NavigationUI.setupWithNavController(bnv, navController)
            CustomUI.bnvCustomView(binding)
            CustomUI.bnvDefaultView(binding)
        }
    }

    private fun currentPage() {
        binding.apply {
            navController.navigate(R.id.showCaseFragment)
            bnv.menu.findItem(R.id.item_showcase).isChecked = true
        }
    }

    private fun setupBnvInActivity() {
        binding.apply {
            bnv.setOnItemSelectedListener {
                when (it.itemId) {

                    R.id.item_myFilms -> {
                        navController.navigate(R.id.myFilmsFragment)
                    }

                    R.id.item_category -> {
                        navController.navigate(R.id.categoryFragment)
                    }

                    R.id.item_showcase -> {
                        navController.navigate(R.id.showCaseFragment)
                    }

                }
                CustomUI.bnvSetOnItemSelectListenerView(binding, it)
                return@setOnItemSelectedListener true
            }
        }
    }

    private fun clickEvent(){
        binding.apply {
            search.setOnClickListener {
                navController.navigate(R.id.searchFragment)
                MainWidget.bnv.visibility = View.GONE
                MainWidget.toolbar.visibility = View.GONE
            }

            profile.setOnClickListener {
                if (appSettings.getLock() == 0){
                    val dialog = AlertDialog.Builder(this@MainActivity)
                    dialog.setTitle("ثبت نام یا ورود")
                    dialog.setMessage("برای وارد شدن به حساب کاربری ابتدا وارد شوید یا ثبت نام کنید")
                    dialog.setPositiveButton("بله") { _, _ ->
                        navController.navigate(R.id.loginFragment)
                        MainWidget.bnv.visibility = View.GONE
                        MainWidget.toolbar.visibility = View.GONE
                    }
                    dialog.setNegativeButton("خیر") { _, _ ->

                    }
                    dialog.show()
                }else {
                    navController.navigate(R.id.userProfileFragment)
                    MainWidget.bnv.visibility = View.GONE
                    MainWidget.toolbar.visibility = View.GONE
                }
            }
        }
    }
}