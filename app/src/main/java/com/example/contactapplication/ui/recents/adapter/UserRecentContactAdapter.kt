package com.example.contactapplication.ui.recents.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ItemRecentContactBinding

class UserRecentContactAdapter(
    private val iClickItemRecentContactListener: IClickItemRecentContactListener
) : BaseRecyclerViewAdapter<UserContactEntity, UserRecentContactAdapter.UserRecentContactViewHolder>() {
    inner class UserRecentContactViewHolder(
        private val viewBinding: ItemRecentContactBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBindData(item: UserContactEntity?) {
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
    fun onClickCallPhone(userContact: UserContactEntity?)
    fun onClickCallVideo(userContact: UserContactEntity?)
    fun onClickSendMessage(userContact: UserContactEntity?)
    fun onClickHistory(userContact: UserContactEntity?)
}