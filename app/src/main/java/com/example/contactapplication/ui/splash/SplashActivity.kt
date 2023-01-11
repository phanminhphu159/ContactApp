package com.example.contactapplication.ui.splash

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import com.example.contactapplication.MainActivity
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.databinding.ActivitySplashBinding
import com.skydoves.bundler.intentOf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

///**
// * Created by ThuanPx on 16/09/2021.
// */
//@SuppressLint("CustomSplashScreen")
//@AndroidEntryPoint
class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>(SplashViewModel::class) {

    override fun inflateViewBinding(inflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(inflater)
    }

    override fun initialize() {
        lifecycleScope.launch {
            delay(1_000)
            intentOf<MainActivity> {
                startActivity(this@SplashActivity)
            }
            finish()
        }
    }
}
