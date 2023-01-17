package com.example.contactapplication.ui.contacts.contactDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.databinding.ItemContactDetailBinding
import com.example.contactapplication.model.remote.dto.UserContactDto

class ContactInfoAdapter :
    BaseRecyclerViewAdapter<UserContactDto, ContactInfoAdapter.UserDetailContactViewHolder>() {
    class UserDetailContactViewHolder(
        private val viewBinding: ItemContactDetailBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBindData(item: UserContactDto?) {
            with(viewBinding) {
                tvUserName.text = item?.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailContactViewHolder {
        return UserDetailContactViewHolder(
            ItemContactDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserDetailContactViewHolder, position: Int) {
        holder.onBindData(getItem(position))
    }
}