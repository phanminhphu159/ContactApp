package com.example.contactapplication.ui.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.FragmentContactsBinding
import com.example.contactapplication.ui.contacts.adapter.IClickItemUserContactListener
import com.example.contactapplication.ui.contacts.adapter.UserContactAdapter


class ContactsFragment : Fragment(), IClickItemUserContactListener {

    private var _binding: FragmentContactsBinding? = null
    private var listUserContact: ArrayList<UserContactDto>? = null
    private var userContactAdapter: UserContactAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ContactsViewModel::class.java)

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setData()
        setAdapter()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
    }


    private fun setAdapter() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        userContactAdapter = UserContactAdapter()
        userContactAdapter?.setData(listUserContact)
        binding?.rvContact?.layoutManager = linearLayoutManager
        binding?.rvContact?.adapter = userContactAdapter
    }

    override fun onClickItemUserContact(userContact: UserContactDto?) {

    }
}