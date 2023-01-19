package com.example.contactapplication.ui.contacts.contactDetail

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ActivityContactDetailBinding
import com.example.contactapplication.ktext.Constant
import com.example.contactapplication.ktext.gson.fromJsonType
import com.example.contactapplication.ktext.image.ImageUtil
import com.example.contactapplication.ktext.recyclerView.initRecyclerViewAdapter
import com.example.contactapplication.ui.contacts.contactDetail.adapter.ContactInfoAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactDetailActivity :
    BaseActivity<ContactDetailViewModel, ActivityContactDetailBinding>(ContactDetailViewModel::class) {

    private var contactInfoAdapter: ContactInfoAdapter? = null
    private var userContact: UserContactEntity? = null

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityContactDetailBinding {
        return ActivityContactDetailBinding.inflate(inflater)
    }

    override fun initialize() {
        getBundleData()
        setAdapter()
        setView()
        setOnClick()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1 && resultCode === RESULT_OK && data != null) {
            val uri = data?.data
            if (uri != null) {

                val imageBitmap = ImageUtil.uriToBitmap(uri)
                viewBinding.ivAvatarUser.setImageBitmap(imageBitmap)

                userContact?.image = ImageUtil.bitmapToByteArray(imageBitmap)
                userContact?.let { viewModel.updateContact(it) }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setView() {
        with(viewBinding) {
            tvContactName.text = userContact?.name
            tvContactPhone.text = userContact?.phone
        }
    }

    private fun getBundleData() {
        val bundle = intent.extras
        val gson = Gson()
        userContact = bundle?.getString("contact")?.let { gson.fromJsonType(it) }
    }

    private fun setAdapter() {
        contactInfoAdapter = ContactInfoAdapter()
        viewBinding.rvContactInfo.initRecyclerViewAdapter(
            contactInfoAdapter,
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
            true
        )
        viewModel.listContactInfo.observe(this) {
            contactInfoAdapter?.replaceData(it.toMutableList())
        }
    }

    private fun setOnClick() {
        with(viewBinding) {
            btnEditImage.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(intent, 1)
            }
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
                            Uri.fromParts("tel", userContact?.phone, null)
                        )
                    )
                }
            }
            btnSendMessage.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", userContact?.phone, null)))
            }
            btnCallVideo.setOnClickListener { }
            ivBackButton.setOnClickListener { onBackPressed() }
        }
    }
}