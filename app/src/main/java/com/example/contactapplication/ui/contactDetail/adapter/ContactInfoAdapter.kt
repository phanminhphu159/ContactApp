package com.example.contactapplication.ui.contactDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.ItemDetailContactBinding

class ContactInfoAdapter :
    BaseRecyclerViewAdapter<UserContactDto, ContactInfoAdapter.UserDetailContactViewHolder>() {
    class UserDetailContactViewHolder(
        private val viewBinding: ItemDetailContactBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBindData(item: UserContactDto?) {
            with(viewBinding) {
                tvUserName.text = item?.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailContactViewHolder {
        return UserDetailContactViewHolder(
            ItemDetailContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserDetailContactViewHolder, position: Int) {
        holder.onBindData(getItem(position))
    }
}