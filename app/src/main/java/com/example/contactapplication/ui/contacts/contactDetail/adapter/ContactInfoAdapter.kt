package com.example.contactapplication.ui.contacts.contactDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ItemContactDetailBinding
import com.example.contactapplication.ktext.image.ImageUtil

class ContactInfoAdapter :
    BaseRecyclerViewAdapter<UserContactEntity, ContactInfoAdapter.UserDetailContactViewHolder>() {
    class UserDetailContactViewHolder(
        private val viewBinding: ItemContactDetailBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBindData(item: UserContactEntity?) {
            with(viewBinding) {
                tvUserName.text = item?.name
                if (item?.image?.isNotEmpty() == true){
                    ivAvatar.setImageBitmap(ImageUtil.byteArrayToBitmap(item.image))
                }
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