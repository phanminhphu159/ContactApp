package com.example.contactapplication.ui.contacts.contactDetail

import com.example.contactapplication.base.viewmodel.BaseViewModel
import com.example.contactapplication.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactDetailViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel(){
    val listContactInfo = appRepository.getContacts()
}