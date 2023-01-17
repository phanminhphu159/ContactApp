package com.example.contactapplication.ui.favorites.addFavorite

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.App
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ActivityFavoriteAddFavoriteContactBinding
import com.example.contactapplication.ktext.recyclerView.initRecyclerViewAdapter
import com.example.contactapplication.model.remote.dto.UserContactDto
import com.example.contactapplication.ui.favorites.FavoritesViewModel
import com.example.contactapplication.ui.favorites.addFavorite.adapter.AddFavoriteContactAdapter
import com.example.contactapplication.ui.favorites.addFavorite.adapter.IClickItemAddFavoriteContactListener


class FavoriteAddActivity :
    BaseActivity<FavoritesViewModel, ActivityFavoriteAddFavoriteContactBinding>(FavoritesViewModel::class),
    IClickItemAddFavoriteContactListener {

    private var listContact: MutableList<UserContactDto>? = null
    private var addFavoriteContactAdapter: AddFavoriteContactAdapter? = null

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityFavoriteAddFavoriteContactBinding {
        return ActivityFavoriteAddFavoriteContactBinding.inflate(inflater)
    }

    override fun initialize() {
        setData()
        setAdapter()
        setOnClick()
        setView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onClickItemUserContact(userContact: UserContactDto?) {
        val updatedUser = UserContactEntity(
            name = userContact?.name,
            phone = userContact?.phone,
            favorite = userContact?.favorite
        )
    }

    private fun setData() {
        listContact = mutableListOf()
        for (userContact in App.listContact) {
            listContact?.add(
                UserContactDto(
                    name = userContact.name,
                    phone = userContact.phone
                )
            )
        }
    }

    private fun setView() {
        with(viewBinding) {
        }
    }

    private fun setAdapter() {
        addFavoriteContactAdapter = AddFavoriteContactAdapter(this)
        addFavoriteContactAdapter?.addData(listContact)
        viewBinding.rvFavoriteContact.initRecyclerViewAdapter(
            addFavoriteContactAdapter,
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false),
            true
        )
    }

    private fun setOnClick() {
        with(viewBinding) {
            btnBack.setOnClickListener { onBackPressed() }
        }
    }
}