package com.example.contactapplication

import android.view.LayoutInflater
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initialize() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        viewBinding.navView.setupWithNavController(navController)
    }
}