package com.example.contactapplication.ui.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.R
import com.example.contactapplication.data.remote.dto.UserContactDto
import com.example.contactapplication.databinding.ItemUserContactBinding
import com.example.contactapplication.databinding.ItemUserContactHeaderBinding


class UserContactAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), IClickItemUserContactListener,
    HeaderItemDecoration.StickyHeaderInterface {
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

    override fun onClickItemUserContact(userContact: UserContactDto?) {
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int
//    {
//
//    }
    =
        (itemPosition downTo 0)
            .map { Pair(isHeader(it), it) }
            .firstOrNull { it.first }?.second ?: RecyclerView.NO_POSITION

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
            headBinding.tvUserName.text = userContactList?.get(headerPosition)?.name?.first().toString()
        }
        /* ...
          here you get your header and can change some data on it
        ... */
    }

    override fun isHeader(itemPosition: Int): Boolean {
        if ( itemPosition == 0 || userContactList?.get(itemPosition)?.name?.first() != userContactList?.get(itemPosition - 1)?.name?.first())
            return true
        return false
        /* ...
      here have to be condition for checking - is item on this position header
    ... */
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
}

interface IClickItemUserContactListener {
    fun onClickItemUserContact(userContact: UserContactDto?)
}

