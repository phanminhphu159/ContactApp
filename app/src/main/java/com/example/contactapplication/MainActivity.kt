package com.example.contactapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.databinding.ActivityMainBinding
import com.example.contactapplication.ktext.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun initialize() {
        setView()
        setOnClick()
    }

    private fun setView() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        viewBinding.bnvFooter.setupWithNavController(navController)
    }

    private fun setOnClick() {
        with(viewBinding) {
            btnPhoneCall.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this@MainActivity,
                        android.Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@MainActivity, arrayOf(android.Manifest.permission.CALL_PHONE),
                        Constant.REQUEST_PHONE_CODE
                    )
                } else {
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "", null)))
                }
            }
        }
    }
}