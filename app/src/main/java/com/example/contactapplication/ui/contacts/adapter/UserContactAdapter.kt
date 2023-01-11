package com.example.contactapplication.ui.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.ItemUserContactBinding


class UserContactAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), IClickItemUserContactListener {
    private var userContactList: List<UserContactDto>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserContactViewHolder(
            ItemUserContactBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        userContactList?.let { (holder as UserContactViewHolder).onBindingData(it[position]) }
    }

    override fun getItemCount(): Int {
        return userContactList?.size ?: 0
    }

    fun setData(userContactList: List<UserContactDto>?) {
        this.userContactList = userContactList
    }

    inner class UserContactViewHolder(inflate: ItemUserContactBinding) :
        RecyclerView.ViewHolder(inflate.root) {

        private val itemBinding: ItemUserContactBinding

        init {
            itemBinding = inflate
        }

        fun onBindingData(userContact: UserContactDto) {
            itemBinding.tvUserName.text = userContact.name
//            itemBinding.ivLike.setOnClickListener {
//                    view ->
//                userContact.setLike(!userContact.getLike())
//                itemBinding.ivLike.setImageResource(if (comment.getLike()) R.drawable.ic_baseline_favorite_24 else R.drawable.ic_baseline_favorite_border_24)
//            }
            itemBinding.layoutItem.setOnClickListener {
                onClickItemUserContact(
                    userContact
                )
            }
        }
    }

    override fun onClickItemUserContact(userContact: UserContactDto?) {
    }
}

interface IClickItemUserContactListener {
    fun onClickItemUserContact(userContact: UserContactDto?)
}

