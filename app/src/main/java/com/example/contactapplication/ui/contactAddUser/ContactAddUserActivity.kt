package com.example.contactapplication.ui.contactAddUser

import android.view.LayoutInflater
import com.example.contactapplication.base.BaseActivity
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
            btnSave.setOnClickListener { }
        }
    }
}