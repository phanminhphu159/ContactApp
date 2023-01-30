package com.example.contactapplication.ui.contacts

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.R
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.FragmentContactsBinding
import com.example.contactapplication.ktext.gson.toJsonType
import com.example.contactapplication.ktext.recyclerView.initRecyclerViewAdapter
import com.example.contactapplication.ui.contacts.adapter.*
import com.example.contactapplication.ui.contacts.contactAddUser.ContactAddUserActivity
import com.example.contactapplication.ui.contacts.contactDetail.ContactDetailActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactsFragment :
    BaseFragment<ContactsViewModel, FragmentContactsBinding>(ContactsViewModel::class),
    IClickItemContactListener {

    private var userContactAdapter: UserContactAdapter? = null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactsBinding {
        return FragmentContactsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setAdapter()
        setData()
    }

    private fun setData() {
        viewModel.listContact.observe(this) {
            userContactAdapter?.replaceRoomData(
                UserContactEntity(
                    name = getString(R.string.add_user_contact),
                    phone = "",
                    favorite = false,
                    image = null,
                    time = null,
                    status = null
                ), 0, it.toMutableList()
            )
        }
    }


    private fun setAdapter() {
        userContactAdapter = UserContactAdapter(this)
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

    override fun onClickItemUserContact(userContact: UserContactEntity?) {
        val mIntent = Intent(activity, ContactDetailActivity::class.java)
        val mBundle = Bundle()
        val gson = Gson()
        mBundle.putString("contact", gson.toJsonType(userContact))
        mIntent.putExtras(mBundle);
        activity?.startActivity(mIntent)
    }

    override fun onClickAddUserContact() {
        activity?.startActivity(Intent(activity, ContactAddUserActivity::class.java))
    }
}