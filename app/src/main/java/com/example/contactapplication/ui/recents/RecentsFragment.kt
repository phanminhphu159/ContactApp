package com.example.contactapplication.ui.recents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.FragmentRecentsBinding
import com.example.contactapplication.ui.contacts.adapter.HeaderItemDecoration
import com.example.contactapplication.ui.contacts.adapter.UserContactAdapter
import com.example.contactapplication.ui.recents.adapter.UserRecentContactAdapter

class RecentsFragment : BaseFragment<RecentsViewModel, FragmentRecentsBinding>(RecentsViewModel::class) {

    private var listRecentContact: ArrayList<UserContactDto>? = null
    private var recentContactAdapter: UserRecentContactAdapter? = null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRecentsBinding {
        return FragmentRecentsBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setData()
        setAdapter()
    }

    private fun setData() {
        listRecentContact = ArrayList()
        listRecentContact?.add(
            UserContactDto(
                "Phan Minh Phú",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Phan Minh Phú",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Phan Minh Phú",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Lê Kim QUân",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Lê Kim QUân",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Lê Kim QUân",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Lê Thanh Vũ",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                "+84905693609",
                "193 Da Nang City"
            )
        )
        listRecentContact?.add(
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
        recentContactAdapter = UserRecentContactAdapter()
        recentContactAdapter?.addData(listRecentContact)
        viewBinding.rvRecentContact.layoutManager = linearLayoutManager
        viewBinding.rvRecentContact.adapter = recentContactAdapter
    }
}