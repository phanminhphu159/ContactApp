package com.example.contactapplication.ui.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.R
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.ItemUserContactBinding
import com.example.contactapplication.databinding.ItemUserContactHeaderBinding


class UserContactAdapter :
    BaseRecyclerViewAdapter<UserContactDto, UserContactAdapter.UserContactViewHolder>(),
    IClickItemUserContactListener, HeaderItemDecoration.StickyHeaderInterface {

    class UserContactViewHolder(
        private val viewBinding: ItemUserContactBinding
    ) : RecyclerView.ViewHolder(viewBinding.root), IClickItemUserContactListener {
        fun onBindData(item: UserContactDto?) {
            with(viewBinding) {
                tvUserName.text = item?.name
//            itemBinding.ivLike.setOnClickListener {
//                    view ->
//            }
                layoutItem.setOnClickListener {
                    onClickItemUserContact(
                        item
                    )
                }
            }
        }

        override fun onClickItemUserContact(userContact: UserContactDto?) {
            TODO("Not yet implemented")
        }
    }

    override fun onClickItemUserContact(userContact: UserContactDto?) {
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int =
        (itemPosition downTo 0).map { Pair(isHeader(it), it) }.firstOrNull { it.first }?.second
            ?: RecyclerView.NO_POSITION

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.item_user_contact_header
        /* ...
      return something like R.layout.view_header
      or add conditions if you have different headers on different positions
    ... */
    }

    override fun bindHeaderData(headBinding: ItemUserContactHeaderBinding, headerPosition: Int) {
        if (headerPosition == RecyclerView.NO_POSITION) headBinding.root.layoutParams.height = 0
        else {
            headBinding.tvUserName.text = getData()[headerPosition].name?.first().toString()
        }
        /* ...
          here you get your header and can change some data on it
        ... */
    }

    override fun isHeader(itemPosition: Int): Boolean {
        if (itemPosition == 0 || getData()[itemPosition].name?.first() != getData()[itemPosition - 1].name?.first()) return true
        return false
        /* ...
      here have to be condition for checking - is item on this position header
    ... */
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): UserContactAdapter.UserContactViewHolder {
        return UserContactAdapter.UserContactViewHolder(
            ItemUserContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserContactViewHolder, position: Int) {
        holder.onBindData(getItem(position))
    }
}

interface IClickItemUserContactListener {
    fun onClickItemUserContact(userContact: UserContactDto?)
}

