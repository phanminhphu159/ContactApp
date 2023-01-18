package com.example.contactapplication.ui.recents

import com.example.contactapplication.base.viewmodel.BaseViewModel
import com.example.contactapplication.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecentsViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {
    val listRecentContacts = appRepository.getRecentContacts()
}