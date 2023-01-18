package com.example.contactapplication.ui.contacts.contactAddUser

import com.example.contactapplication.base.viewmodel.BaseViewModel
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactAddUserViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {
    fun addContact(user: UserContactEntity) {
        appRepository.insert(user)
    }
}