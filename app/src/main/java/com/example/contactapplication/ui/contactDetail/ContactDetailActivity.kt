package com.example.contactapplication.ui.contactDetail

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.ActivityContactDetailBinding
import com.example.contactapplication.ui.contactDetail.adapter.ContactInfoAdapter


class ContactDetailActivity :
    BaseActivity<ContactDetailViewModel, ActivityContactDetailBinding>(ContactDetailViewModel::class) {

    private var listContactInfo: ArrayList<UserContactDto>? = null
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
        listContactInfo = ArrayList()
        listContactInfo?.add(
            UserContactDto(
                "Phan Minh Phú",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Phan Minh Phú",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Phan Minh Phú",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Kim QUân",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Kim QUân",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Kim QUân",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Thanh Vũ",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                "+84905693609",
                "193 Da Nang City"
            )
        )
    }

    private fun setView() {
        with(viewBinding){
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
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactInfoAdapter = ContactInfoAdapter()
        contactInfoAdapter?.addData(listContactInfo)
        viewBinding.rvContactInfo.layoutManager = linearLayoutManager
        viewBinding.rvContactInfo.adapter = contactInfoAdapter
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
                        REQUEST_CODE
                    )
                } else {
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactPhone, null)))
                }
            }
            btnSendMessage.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", contactPhone, null)))
            }
            btnCallVideo.setOnClickListener { }
            ivBackButton.setOnClickListener { onBackPressed() }
        }
    }

    companion object {
        private const val REQUEST_CODE = 12345
    }
}