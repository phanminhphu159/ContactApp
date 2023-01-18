package com.example.contactapplication.ui.favorites.addFavorite

import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.R
import com.example.contactapplication.base.BaseActivity
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.ActivityFavoriteAddFavoriteContactBinding
import com.example.contactapplication.ktext.boolean.isNotTrue
import com.example.contactapplication.ktext.boolean.isTrue
import com.example.contactapplication.ktext.context.showDialog
import com.example.contactapplication.ktext.recyclerView.initRecyclerViewAdapter
import com.example.contactapplication.ui.contacts.ContactsViewModel
import com.example.contactapplication.ui.favorites.addFavorite.adapter.AddFavoriteContactAdapter
import com.example.contactapplication.ui.favorites.addFavorite.adapter.IClickItemAddFavoriteContactListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteAddActivity :
    BaseActivity<ContactsViewModel, ActivityFavoriteAddFavoriteContactBinding>(ContactsViewModel::class),
    IClickItemAddFavoriteContactListener {

    private var addFavoriteContactAdapter: AddFavoriteContactAdapter? = null

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityFavoriteAddFavoriteContactBinding {
        return ActivityFavoriteAddFavoriteContactBinding.inflate(inflater)
    }

    override fun initialize() {
        setView()
        setAdapter()
        setData()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onClickItemUserContact(userContact: UserContactEntity?) {
        val updatedUser = UserContactEntity(
            uid = userContact?.uid,
            name = userContact?.name,
            phone = userContact?.phone,
            favorite = !userContact?.favorite!!
        )
        viewModel.updateContact(updatedUser)
        showDialog(context = this@FavoriteAddActivity, content = getString(R.string.add_favorite_contact_successfully))
    }

    private fun setData() {
        viewModel.listContact.observe(this) {
            addFavoriteContactAdapter?.replaceData(it)
        }
    }

    private fun setView() {
        setOnClick()
        with(viewBinding) {
        }
    }

    private fun setAdapter() {
        addFavoriteContactAdapter = AddFavoriteContactAdapter(this)
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