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
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false),
            true
        )
    }
}