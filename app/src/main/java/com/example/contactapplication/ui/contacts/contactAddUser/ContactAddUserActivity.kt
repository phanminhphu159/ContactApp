package com.example.contactapplication.ui.contacts.contactAddUser

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import android.view.LayoutInflater
import com.example.contactapplication.R
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ActivityContactAddUserBinding
import com.example.contactapplication.ktext.context.showDialog
import com.example.contactapplication.ktext.image.ImageUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactAddUserActivity :
    BaseActivity<ContactAddUserViewModel, ActivityContactAddUserBinding>(ContactAddUserViewModel::class) {

    var contactImage : Bitmap? = null

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityContactAddUserBinding {
        return ActivityContactAddUserBinding.inflate(inflater)
    }

    override fun initialize() {
        setOnClick()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1 && resultCode === RESULT_OK && data != null) {
            val uri = data?.data
            if (uri != null) {
                contactImage = ImageUtil.uriToBitmap(uri)
                viewBinding.ivAvatarUser.setImageBitmap(contactImage)
            }
        }
    }

    private fun setOnClick() {
        with(viewBinding) {
            btnClose.setOnClickListener { onBackPressed() }
            btnSave.setOnClickListener {
                saveNewContact()
            }
            btnEditImage.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(intent, 1)
            }
        }
    }

    private fun saveNewContact() {
        with(viewBinding) {
            if (etContactName.text.toString().isEmpty() || etContactPhone.text.toString()
                    .isEmpty()
            ) {
                showDialog(
                    context = this@ContactAddUserActivity,
                    content = getString(R.string.pls_fill_in_contact_name_or_password)
                )

            } else {
//                userContact?.image = ImageUtil.bitmapToByteArray(imageBitmap)
//                userContact?.let { viewModel.updateContact(it) }
                val userContactEntity = UserContactEntity(
                    name = etContactName.text.toString(),
                    phone = etContactPhone.text.toString(),
                    favorite = false,
                    image = ImageUtil.bitmapToByteArray(contactImage)
                )
                viewModel.addContact(userContactEntity)
                etContactName.text?.clear()
                etContactPhone.text?.clear()
                viewBinding.ivAvatarUser.setImageResource(R.drawable.ic_user_avatar)
                showDialog(
                    context = this@ContactAddUserActivity,
                    content = getString(R.string.add_contact_successfully)
                )
            }
        }
    }

}