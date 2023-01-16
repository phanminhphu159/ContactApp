package com.example.contactapplication.ui.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.R
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.FragmentContactsBinding
import com.example.contactapplication.ui.contactDetail.ContactDetailActivity
import com.example.contactapplication.ui.contacts.adapter.*


class ContactsFragment :
    BaseFragment<ContactsViewModel, FragmentContactsBinding>(ContactsViewModel::class),
    IClickItemUserContactListener {

    private var listUserContact: MutableList<UserContactDto>? = null
    private var userContactAdapter: UserContactAdapter? = null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactsBinding {
        return FragmentContactsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setData()
        setAdapter()
    }

    private fun setData() {
        listUserContact = mutableListOf()
        listUserContact?.add(
            UserContactDto(
                name = getString(R.string.add_user_contact)
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Phan Minh Phú",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Phan Minh Phú",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Phan Minh Phú",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Kim QUân",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Kim QUân",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Kim QUân",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Thanh Vũ11",
                "+849056936022229",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                "+84905693609",
                "193 Da Nang City"
            )
        )
    }


    private fun setAdapter() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        userContactAdapter = UserContactAdapter(this)
        userContactAdapter?.addData(listUserContact)
        viewBinding.rvContact.layoutManager = linearLayoutManager
        viewBinding.rvContact.adapter = userContactAdapter
//        viewBinding.rvContact.addItemDecoration(
//            HeaderItemDecoration(
//                viewBinding.rvContact as RecyclerView,
//                userContactAdapter!!
//            )
//        )
    }

    override fun onClickItemUserContact(userContact: UserContactDto?) {
        val mIntent = Intent(activity, ContactDetailActivity::class.java)
        val mBundle = Bundle()
        mBundle.putString("name", userContact?.name)
        mBundle.putString("phone", userContact?.phone)
        mBundle.putString("address", userContact?.address)
        mIntent.putExtras(mBundle);
        activity?.startActivity(mIntent)
    }

    override fun onClickAddUserContact() {
    }
}