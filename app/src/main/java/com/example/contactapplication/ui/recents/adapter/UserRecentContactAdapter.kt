package com.example.contactapplication.ui.recents.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.ItemRecentContactBinding

class UserRecentContactAdapter :
    BaseRecyclerViewAdapter<UserContactDto, UserRecentContactAdapter.UserRecentContactViewHolder>() {
    class UserRecentContactViewHolder(
        private val viewBinding: ItemRecentContactBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBindData(item: UserContactDto?) {
            with(viewBinding) {
                tvUserName.text = item?.name
                layoutUserContact.setOnClickListener {
                    if (layoutContactStuff.isVisible){
                        lineHorizontal50.visibility = View.GONE
                        layoutContactStuff.visibility = View.GONE
                    }else{
                        lineHorizontal50.visibility = View.VISIBLE
                        layoutContactStuff.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecentContactViewHolder {
        return UserRecentContactViewHolder(
            ItemRecentContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserRecentContactViewHolder, position: Int) {
        holder.onBindData(getItem(position))
    }
}

interface IClickItemUserContactListener {
    fun onClickItemUserContact(userContact: UserContactDto?)
}