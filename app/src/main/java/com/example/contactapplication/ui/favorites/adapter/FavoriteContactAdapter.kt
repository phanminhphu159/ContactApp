package com.example.contactapplication.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.R
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.databinding.ItemContactHeaderBinding
import com.example.contactapplication.databinding.ItemContactUserBinding
import com.example.contactapplication.databinding.ItemFavoriteContactBinding
import com.example.contactapplication.model.remote.dto.UserContactDto
import com.example.contactapplication.ui.contacts.adapter.HeaderItemDecoration


class FavoriteContactAdapter(
    private val iClickItemUserContactListener: IClickItemFavoriteContactListener
) : BaseRecyclerViewAdapter<UserContactDto, RecyclerView.ViewHolder>() {

    inner class FavoriteContactViewHolder(
        private val viewBinding: ItemFavoriteContactBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBindData(item: UserContactDto?) {
            with(viewBinding) {
                tvUserName.text = item?.name
                layoutUserContact.setOnClickListener {
                    iClickItemUserContactListener.onClickItemUserContact(
                        item
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        return FavoriteContactViewHolder(
            ItemFavoriteContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FavoriteContactViewHolder).onBindData(getItem(position))
    }
}

interface IClickItemFavoriteContactListener {
    fun onClickItemUserContact(userContact: UserContactDto?)
}

