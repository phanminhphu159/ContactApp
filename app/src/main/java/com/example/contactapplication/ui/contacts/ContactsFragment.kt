package com.example.contactapplication.ui.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.App
import com.example.contactapplication.R
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.model.remote.dto.UserContactDto
import com.example.contactapplication.databinding.FragmentContactsBinding
import com.example.contactapplication.ktext.recyclerView.initRecyclerViewAdapter
import com.example.contactapplication.ui.contacts.contactAddUser.ContactAddUserActivity
import com.example.contactapplication.ui.contacts.contactDetail.ContactDetailActivity
import com.example.contactapplication.ui.contacts.adapter.*


class ContactsFragment :
    BaseFragment<ContactsViewModel, FragmentContactsBinding>(ContactsViewModel::class),
    IClickItemContactListener {

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
        for (userContact in App.listContact) {
            listUserContact?.add(
                UserContactDto(
                    name = userContact.name,
                    phone = userContact.phone
                )
            )
        }
    }


    private fun setAdapter() {
        userContactAdapter = UserContactAdapter(this)
        listUserContact?.add(
            0, UserContactDto(
                name = getString(R.string.add_user_contact)
            )
        )
        userContactAdapter?.addData(listUserContact)
        viewBinding.rvFavoriteContact.initRecyclerViewAdapter(
            userContactAdapter,
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false),
            true
        )
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
        activity?.startActivity(Intent(activity, ContactAddUserActivity::class.java))
    }
}