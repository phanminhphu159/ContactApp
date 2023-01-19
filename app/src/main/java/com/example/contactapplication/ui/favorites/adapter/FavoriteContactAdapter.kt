package com.example.contactapplication.ui.favorites.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapplication.base.recyclerview.BaseRecyclerViewAdapter
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ItemFavoriteContactBinding
import com.example.contactapplication.ktext.image.ImageUtil


class FavoriteContactAdapter(
    private val iClickItemUserContactListener: IClickItemFavoriteContactListener
) : BaseRecyclerViewAdapter<UserContactEntity, RecyclerView.ViewHolder>() {

    inner class FavoriteContactViewHolder(
        private val viewBinding: ItemFavoriteContactBinding
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
    fun onClickItemUserContact(userContact: UserContactEntity?)
}

