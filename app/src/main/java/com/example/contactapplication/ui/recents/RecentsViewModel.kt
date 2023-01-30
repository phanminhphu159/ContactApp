package com.example.contactapplication.ui.recents

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contactapplication.base.viewmodel.BaseViewModel
import com.example.contactapplication.data.entity.UserContactEntity
import com.example.contactapplication.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecentsViewModel @Inject constructor(
    private val appRepository: AppRepository,
) : BaseViewModel() {
    val listRecentContacts = MutableLiveData<MutableList<UserContactEntity>>()

    fun getCallLogsData(context: Context?){
        listRecentContacts.value = appRepository.getLogContacts(context)
    }
}