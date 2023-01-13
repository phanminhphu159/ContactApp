package com.example.contactapplication.ui.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.R
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.FragmentContactsBinding
import com.example.contactapplication.ktext.context.replaceFragment
import com.example.contactapplication.ui.contactDetail.ContactDetailFragment
import com.example.contactapplication.ui.contacts.adapter.*


class ContactsFragment :
    BaseFragment<ContactsViewModel, FragmentContactsBinding>(ContactsViewModel::class), IClickItemUserContactListener {

    private var listUserContact: ArrayList<UserContactDto>? = null
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
        listUserContact = ArrayList()
        listUserContact?.add(
            UserContactDto(
                "Phan Minh Phú",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Phan Minh Phú",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Phan Minh Phú",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Kim QUân",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Kim QUân",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Kim QUân",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Lê Thanh Vũ",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                905693609,
                "193 Da Nang City"
            )
        )
        listUserContact?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                905693609,
                "193 Da Nang City"
            )
        )
    }


    private fun setAdapter() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        userContactAdapter = UserContactAdapter()
        userContactAdapter?.addData(listUserContact)
        viewBinding.rvContact.layoutManager = linearLayoutManager
        viewBinding.rvContact.adapter = userContactAdapter
        viewBinding.rvContact.addItemDecoration(
            HeaderItemDecoration(
                viewBinding.rvContact as RecyclerView,
                userContactAdapter!!
            )
        )
    }

    override fun onClickItemUserContact(userContact: UserContactDto?) {
        replaceFragment(R.id.nav_host_fragment_activity_main, ContactDetailFragment())
    }
}