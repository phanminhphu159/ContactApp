package com.example.contactapplication.ui.favorites

import com.example.contactapplication.base.viewmodel.BaseViewModel
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {
    val listFavorites = appRepository.getContacts()
}