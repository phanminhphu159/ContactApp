package com.example.contactapplication.ui.recents.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.model.remote.dto.UserContactDto
import com.example.contactapplication.databinding.ItemRecentContactBinding

class UserRecentContactAdapter(
    private val iClickItemRecentContactListener: IClickItemRecentContactListener
) : BaseRecyclerViewAdapter<UserContactDto, UserRecentContactAdapter.UserRecentContactViewHolder>() {
    inner class UserRecentContactViewHolder(
        private val viewBinding: ItemRecentContactBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBindData(item: UserContactDto?) {
            with(viewBinding) {
                tvUserName.text = item?.name
                layoutUserContact.setOnClickListener {
                    if (layoutContactStuff.isVisible) {
                        lineHorizontal50.visibility = View.GONE
                        layoutContactStuff.visibility = View.GONE
                    } else {
                        lineHorizontal50.visibility = View.VISIBLE
                        layoutContactStuff.visibility = View.VISIBLE
                    }
                }
                btnCallPhone.setOnClickListener {
                    iClickItemRecentContactListener.onClickCallPhone(item)
                }
                btnCallVideo.setOnClickListener {
                    iClickItemRecentContactListener.onClickCallVideo(
                        item
                    )
                }
                btnSendMessage.setOnClickListener {
                    iClickItemRecentContactListener.onClickSendMessage(
                        item
                    )
                }
                btnHistoryContact.setOnClickListener {
                    iClickItemRecentContactListener.onClickHistory(
                        item
                    )
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

interface IClickItemRecentContactListener {
    fun onClickCallPhone(userContact: UserContactDto?)
    fun onClickCallVideo(userContact: UserContactDto?)
    fun onClickSendMessage(userContact: UserContactDto?)
    fun onClickHistory(userContact: UserContactDto?)
}