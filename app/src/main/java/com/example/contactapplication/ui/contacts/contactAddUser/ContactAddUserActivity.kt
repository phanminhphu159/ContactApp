package com.example.contactapplication.ui.contacts.contactAddUser

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import com.example.contactapplication.R
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ActivityContactAddUserBinding
import com.example.contactapplication.ktext.context.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactAddUserActivity :
    BaseActivity<ContactAddUserViewModel, ActivityContactAddUserBinding>(ContactAddUserViewModel::class) {

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityContactAddUserBinding {
        return ActivityContactAddUserBinding.inflate(inflater)
    }

    override fun initialize() {
        setOnClick()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setOnClick() {
        with(viewBinding) {
            btnClose.setOnClickListener { onBackPressed() }
            btnSave.setOnClickListener {
                saveNewContact()
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
                val userContactEntity = UserContactEntity(
                    name = etContactName.text.toString(),
                    phone = etContactPhone.text.toString(),
                    favorite = false
                )
                viewModel.addContact(userContactEntity)
                etContactName.text?.clear()
                etContactPhone.text?.clear()
                showDialog(
                    context = this@ContactAddUserActivity,
                    content = getString(R.string.add_contact_successfully)
                )
            }
        }
    }

}