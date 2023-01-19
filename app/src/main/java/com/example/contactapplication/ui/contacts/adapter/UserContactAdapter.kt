package com.example.contactapplication.ui.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.R
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ItemContactHeaderBinding
import com.example.contactapplication.databinding.ItemContactUserBinding
import com.example.contactapplication.ktext.image.ImageUtil

class UserContactAdapter(
    private val iClickItemUserContactListener: IClickItemContactListener
) :
    BaseRecyclerViewAdapter<UserContactEntity, RecyclerView.ViewHolder>(),
    HeaderItemDecoration.StickyHeaderInterface {

    private val headerType = 1
    private val itemType = 2

    inner class UserContactViewHolder(
        private val viewBinding: ItemContactUserBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBindData(item: UserContactEntity?) {
            with(viewBinding) {
                tvUserName.text = item?.name
                if (item?.image?.isNotEmpty() == true){
                    ivAvatar.setImageBitmap(ImageUtil.byteArrayToBitmap(item.image))
                }
                layoutUserContact.setOnClickListener {
                    iClickItemUserContactListener.onClickItemUserContact(
                        item
                    )
                }
            }
        }
    }

    inner class HeaderContactViewHolder(
        private val viewBinding: ItemContactUserBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBindHeader(item: UserContactEntity?) {
            with(viewBinding) {
                tvUserName.text = item?.name
                ivAvatar.setImageResource(R.drawable.ic_add_user)
                layoutUserContact.setOnClickListener {
                    iClickItemUserContactListener.onClickAddUserContact(
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {

        if (viewType == headerType)
            return HeaderContactViewHolder(
                ItemContactUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        return UserContactViewHolder(
            ItemContactUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == headerType) {
            (holder as HeaderContactViewHolder).onBindHeader(getItem(position))
        } else {
            (holder as UserContactViewHolder).onBindData(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) headerType else itemType
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int =
        (itemPosition downTo 0).map { Pair(isHeader(it), it) }.firstOrNull { it.first }?.second
            ?: RecyclerView.NO_POSITION

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.item_contact_header
        /* ...
      return something like R.layout.view_header
      or add conditions if you have different headers on different positions
    ... */
    }

    override fun bindHeaderData(headBinding: ItemContactHeaderBinding, headerPosition: Int) {
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
}

interface IClickItemContactListener {
    fun onClickItemUserContact(userContact: UserContactEntity?)
    fun onClickAddUserContact()
}

