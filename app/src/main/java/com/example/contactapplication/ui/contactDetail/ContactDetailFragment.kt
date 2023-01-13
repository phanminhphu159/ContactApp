package com.example.contactapplication.ui.contactDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.FragmentContactDetailBinding
import com.example.contactapplication.databinding.FragmentContactsBinding
import com.example.contactapplication.databinding.FragmentRecentsBinding
import com.example.contactapplication.ui.contactDetail.adapter.ContactInfoAdapter
import com.example.contactapplication.ui.recents.adapter.UserRecentContactAdapter

class ContactDetailFragment : BaseFragment<ContactDetailViewModel, FragmentContactDetailBinding>(ContactDetailViewModel::class) {

    private var listContactInfo: ArrayList<UserContactDto>? = null
    private var contactInfoAdapter: ContactInfoAdapter? = null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactDetailBinding {
        return FragmentContactDetailBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setData()
        setAdapter()
    }

    private fun setData() {
        listContactInfo = ArrayList()
        listContactInfo?.add(
            UserContactDto(
                "Phan Minh Phú",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Phan Minh Phú",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Phan Minh Phú",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Hồng Mẫn",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Bùi Đăng Dương",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Kim QUân",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Kim QUân",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Kim QUân",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Lê Thanh Vũ",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
            UserContactDto(
                "Võ Anh Nguyên",
                905693609,
                "193 Da Nang City"
            )
        )
        listContactInfo?.add(
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
        contactInfoAdapter = ContactInfoAdapter()
        contactInfoAdapter?.addData(listContactInfo)
        viewBinding.rvContactInfo.layoutManager = linearLayoutManager
        viewBinding.rvContactInfo.adapter = contactInfoAdapter
    }
}