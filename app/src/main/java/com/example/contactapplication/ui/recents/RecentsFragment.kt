package com.example.contactapplication.ui.recents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.App
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.model.remote.dto.UserContactDto
import com.example.contactapplication.databinding.FragmentRecentsBinding
import com.example.contactapplication.ktext.Constant
import com.example.contactapplication.ui.recents.adapter.IClickItemRecentContactListener
import com.example.contactapplication.ui.recents.adapter.UserRecentContactAdapter

class RecentsFragment :
    BaseFragment<RecentsViewModel, FragmentRecentsBinding>(RecentsViewModel::class),
    IClickItemRecentContactListener {

    private var listRecentContact: MutableList<UserContactDto>? = null
    private var recentContactAdapter: UserRecentContactAdapter? = null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecentsBinding {
        return FragmentRecentsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setData()
        setAdapter()
    }

    override fun onClickCallPhone(userContact: UserContactDto?) {
        if (activity?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.CALL_PHONE
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(android.Manifest.permission.CALL_PHONE),
                    Constant.REQUEST_PHONE_CODE
                )
            }
        } else {
            startActivity(
                Intent(
                    Intent.ACTION_DIAL,
                    Uri.fromParts("tel", userContact?.phone, null)
                )
            )
        }
    }

    override fun onClickCallVideo(userContact: UserContactDto?) {
    }

    override fun onClickSendMessage(userContact: UserContactDto?) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", userContact?.phone, null)))
    }

    override fun onClickHistory(userContact: UserContactDto?) {
    }

    private fun setData() {
        listRecentContact = mutableListOf()
        for (userContact in App.listContact) {
            listRecentContact?.add(
                UserContactDto(
                    name = userContact.name,
                    phone = userContact.phone
                )
            )
        }
    }

    private fun setAdapter() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recentContactAdapter = UserRecentContactAdapter(this)
        recentContactAdapter?.addData(listRecentContact)
        viewBinding.rvRecentContact.layoutManager = linearLayoutManager
        viewBinding.rvRecentContact.adapter = recentContactAdapter
    }
}