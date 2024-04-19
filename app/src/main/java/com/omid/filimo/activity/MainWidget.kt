package com.omid.filimo.activity

import android.annotation.SuppressLint
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

@SuppressLint("StaticFieldLeak")
object MainWidget {

    lateinit var bnv: BottomNavigationView
    lateinit var toolbar: Toolbar
    lateinit var clShowCase: ConstraintLayout
    lateinit var clCategoryMyFilms: ConstraintLayout
}