package com.example.contactapplication.ui.contacts.contactDetail

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.App
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.model.remote.dto.UserContactDto
import com.example.contactapplication.databinding.ActivityContactDetailBinding
import com.example.contactapplication.ktext.Constant
import com.example.contactapplication.ktext.recyclerView.initRecyclerViewAdapter
import com.example.contactapplication.ui.contacts.contactDetail.adapter.ContactInfoAdapter


class ContactDetailActivity :
    BaseActivity<ContactDetailViewModel, ActivityContactDetailBinding>(ContactDetailViewModel::class) {

    private var listContactInfo: MutableList<UserContactDto>? = null
    private var contactInfoAdapter: ContactInfoAdapter? = null
    private var contactName: String? = null
    private var contactPhone: String? = null
    private var contactAddress: String? = null


    override fun inflateViewBinding(inflater: LayoutInflater): ActivityContactDetailBinding {
        return ActivityContactDetailBinding.inflate(inflater)
    }

    override fun initialize() {
        getBundleData()
        setData()
        setAdapter()
        setOnClick()
        setView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setData() {
        listContactInfo = mutableListOf()
        for (userContact in App.listContact) {
            listContactInfo?.add(
                UserContactDto(
                    name = userContact.name,
                    phone = userContact.phone
                )
            )
        }
    }

    private fun setView() {
        with(viewBinding) {
            tvContactName.text = contactName
            tvContactPhone.text = contactPhone
        }
    }

    private fun getBundleData() {
        val bundle = intent.extras
        contactName = bundle?.getString("name")
        contactPhone = bundle?.getString("phone")
        contactAddress = bundle?.getString("address")
    }

    private fun setAdapter() {
        contactInfoAdapter = ContactInfoAdapter()
        contactInfoAdapter?.addData(listContactInfo)
        viewBinding.rvContactInfo.initRecyclerViewAdapter(
            contactInfoAdapter,
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
            true
        )
    }

    private fun setOnClick() {
        with(viewBinding) {
            btnCallPhone.setOnClickListener {
                if (ContextCompat.checkSelfPermission(
                        this@ContactDetailActivity,
                        android.Manifest.permission.CALL_PHONE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this@ContactDetailActivity, arrayOf(android.Manifest.permission.CALL_PHONE),
                        Constant.REQUEST_PHONE_CODE
                    )
                } else {
                    startActivity(
                        Intent(
                            Intent.ACTION_DIAL,
                            Uri.fromParts("tel", contactPhone, null)
                        )
                    )
                }
            }
            btnSendMessage.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contactPhone, null)))
            }
            btnCallVideo.setOnClickListener { }
            ivBackButton.setOnClickListener { onBackPressed() }
        }
    }
}