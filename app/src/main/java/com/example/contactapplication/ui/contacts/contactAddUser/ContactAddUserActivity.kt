package com.example.contactapplication.ui.contacts.contactAddUser

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import com.example.contactapplication.App
import com.example.contactapplication.R
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ActivityContactAddUserBinding


class ContactAddUserActivity :
    BaseActivity<ContactAddUserViewModel, ActivityContactAddUserBinding>(ContactAddUserViewModel::class) {

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityContactAddUserBinding {
        return ActivityContactAddUserBinding.inflate(inflater)
    }

    override fun initialize() {
        setView()
        setOnClick()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun setView() {
        with(viewBinding) {
        }
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
                AlertDialog.Builder(this@ContactAddUserActivity)
                    .setTitle(R.string.notification)
                    .setMessage(R.string.pls_fill_in_contact_name_or_password) // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(
                        R.string.ok,
                        DialogInterface.OnClickListener { dialog, which ->
                            // Continue with delete operation
                        }) // A null listener allows the button to dismiss the dialog and take no further action.
                    .setCancelable(false)
                    .show()
            }else{
                val userContactEntity = UserContactEntity(
                    name = etContactName.text.toString(),
                    phone = etContactPhone.text.toString(),
                    favorite = false
                )
                App.databaseDao.insert(userContactEntity)
                etContactName.text?.clear()
                etContactPhone.text?.clear()
            }
        }
    }

}