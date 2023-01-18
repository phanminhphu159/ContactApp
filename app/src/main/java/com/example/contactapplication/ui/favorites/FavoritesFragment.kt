package com.example.contactapplication.ui.favorites

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contactapplication.App
import com.example.contactapplication.R
import com.example.contactapplication.base.fragment.BaseFragment
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.databinding.FragmentFavoritesBinding
import com.example.contactapplication.ktext.Constant
import com.example.contactapplication.ktext.recyclerView.initRecyclerViewAdapter
import com.example.contactapplication.ui.favorites.adapter.FavoriteContactAdapter
import com.example.contactapplication.ui.favorites.adapter.IClickItemFavoriteContactListener
import com.example.contactapplication.ui.favorites.addFavorite.FavoriteAddActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragment<FavoritesViewModel, FragmentFavoritesBinding>(FavoritesViewModel::class),
    IClickItemFavoriteContactListener {

    private var favoriteContactAdapter: FavoriteContactAdapter? = null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoritesBinding {
        return FragmentFavoritesBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        setView()
        setData()
    }

    private fun setData() {
        viewModel.listFavorites.observe(this) {
            favoriteContactAdapter?.clearData(true)
            for ( contact in it ){
                if (contact.favorite == true){
                    favoriteContactAdapter?.itemCount?.let { count ->
                        favoriteContactAdapter?.addItem(contact,
                            count
                        )
                    }
                }
            }
            if (!favoriteContactAdapter?.getData().isNullOrEmpty()) {
                with(viewBinding) {
                    rvFavoriteContact.visibility = View.VISIBLE
                    layoutTitle.visibility = View.VISIBLE
                    layoutNoData.visibility = View.GONE
                }
            }else{
                with(viewBinding) {
                    rvFavoriteContact.visibility = View.GONE
                    layoutTitle.visibility = View.GONE
                    layoutNoData.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setView() {
        setOnClick()
        setAdapter()
    }

    private fun setAdapter() {
        favoriteContactAdapter = FavoriteContactAdapter(this)
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

    override fun onClickItemUserContact(userContact: UserContactEntity?) {
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