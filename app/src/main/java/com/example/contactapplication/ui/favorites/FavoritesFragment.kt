package com.example.contactapplication.ui.favorites

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactapplication.App
import com.example.contactapplication.R
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.model.remote.dto.UserContactDto
import com.example.contactapplication.databinding.FragmentFavoritesBinding
import com.example.contactapplication.ktext.Constant
import com.example.contactapplication.ktext.recyclerView.initRecyclerViewAdapter
import com.example.contactapplication.ui.contacts.contactDetail.ContactDetailActivity
import com.example.contactapplication.ui.favorites.adapter.FavoriteContactAdapter
import com.example.contactapplication.ui.favorites.adapter.IClickItemFavoriteContactListener
import com.example.contactapplication.ui.favorites.addFavorite.FavoriteAddActivity

class FavoritesFragment :
    BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(FavoritesViewModel::class),
    IClickItemFavoriteContactListener {

    private var listFavoriteContact: MutableList<UserContactDto>? = null
    private var favoriteContactAdapter: FavoriteContactAdapter? = null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setData()
        setView()
    }

    private fun setData() {
        listFavoriteContact = mutableListOf()
        for (userContact in App.listContact) {
            listFavoriteContact?.add(
                UserContactDto(
                    name = userContact.name,
                    phone = userContact.phone
                )
            )
        }
    }

    private fun setView() {
        setOnClick()
        if (!listFavoriteContact.isNullOrEmpty()) {
            setData()
            with(viewBinding) {
                rvFavoriteContact.visibility = View.VISIBLE
                layoutTitle.visibility = View.VISIBLE
                layoutNoData.visibility = View.GONE
            }
            setAdapter()
        }
    }


    private fun setAdapter() {
        favoriteContactAdapter = FavoriteContactAdapter(this)
        favoriteContactAdapter?.addData(listFavoriteContact)
        viewBinding.rvFavoriteContact.initRecyclerViewAdapter(
            favoriteContactAdapter,
            GridLayoutManager(requireContext(), 3),
            false
        )
    }

    private fun setOnClick() {
        with(viewBinding) {
            btnAddFavorite.setOnClickListener {
                activity?.startActivity(Intent(activity, FavoriteAddActivity::class.java))
            }
            btnAdd.setOnClickListener {
                activity?.startActivity(Intent(activity, FavoriteAddActivity::class.java))
            }
        }
    }

    override fun onClickItemUserContact(userContact: UserContactDto?) {
        if (activity?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    android.Manifest.permission.CALL_PHONE
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(android.Manifest.permission.CALL_PHONE),
                    Constant.REQUEST_PHONE_CODE
                )
            }
        } else {
            startActivity(
                Intent(
                    Intent.ACTION_DIAL,
                    Uri.fromParts("tel", userContact?.phone, null)
                )
            )
        }
    }
}