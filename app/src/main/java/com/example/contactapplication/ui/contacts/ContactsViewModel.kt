package com.example.contactapplication.ui.contacts

import com.example.contactapplication.base.viewmodel.BaseViewModel
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {
    val listContact = appRepository.getContacts()

    fun updateContact(user: UserContactEntity) {
        appRepository.updateContact(user)
    }
}