package com.example.contactapplication.ui.recents

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.FragmentRecentsBinding
import com.example.contactapplication.ktext.Constant
import com.example.contactapplication.ktext.recyclerView.initRecyclerViewAdapter
import com.example.contactapplication.ui.recents.adapter.IClickItemRecentContactListener
import com.example.contactapplication.ui.recents.adapter.UserRecentContactAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentsFragment :
    BaseFragment<RecentsViewModel, FragmentRecentsBinding>(RecentsViewModel::class),
    IClickItemRecentContactListener {

    private var recentContactAdapter: UserRecentContactAdapter? = null

    override fun inflateViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentRecentsBinding {
        return FragmentRecentsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setAdapter()
        setData()
        showContacts()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constant.PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                viewModel.getCallLogWithDuration(context)
            } else {
                Toast.makeText(context, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    override fun onClickCallPhone(userContact: UserContactEntity?) {
        if (activity?.let {
                ContextCompat.checkSelfPermission(
                    it, android.Manifest.permission.CALL_PHONE
                )
            } != PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(android.Manifest.permission.CALL_PHONE), Constant.REQUEST_PHONE_CODE
                )
            }
        } else {
            startActivity(
                Intent(
                    Intent.ACTION_DIAL, Uri.fromParts("tel", userContact?.phone, null)
                )
            )
        }
    }

    override fun onClickCallVideo(userContact: UserContactEntity?) {
    }

    override fun onClickSendMessage(userContact: UserContactEntity?) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", userContact?.phone, null)))
    }

    override fun onClickHistory(userContact: UserContactEntity?) {
    }

    private fun setData() {
        viewModel.listRecentContacts.observe(this) {
            recentContactAdapter?.replaceData(it.toMutableList())
        }
    }

    private fun setAdapter() {
        recentContactAdapter = UserRecentContactAdapter(this)
        viewBinding.rvRecentContact.initRecyclerViewAdapter(
            recentContactAdapter,
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false),
            true
        )
    }

    private fun showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context?.let { checkSelfPermission(it,Manifest.permission.READ_CONTACTS) } != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                Constant.PERMISSIONS_REQUEST_READ_CONTACTS
            )
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.
            viewModel.getCallLogWithDuration(context)
        }
    }
}