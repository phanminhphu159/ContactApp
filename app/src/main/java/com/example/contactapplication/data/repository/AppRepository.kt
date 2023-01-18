package com.example.contactapplication.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.contactapplication.App
import com.example.contactapplication.data.entity.UserContactEntity

interface AppRepository {
    fun getContacts(): LiveData<MutableList<UserContactEntity>>
    fun getRecentContacts(): LiveData<MutableList<UserContactEntity>>
    fun insert( contact: UserContactEntity)
    fun updateContact( contact: UserContactEntity)
}

class DatabaseRepository : AppRepository {
    @WorkerThread
    override fun getContacts(): LiveData<MutableList<UserContactEntity>> {
        return App.listContact
    }

    override fun getRecentContacts(): LiveData<MutableList<UserContactEntity>> {
        return App.listContact
    }

    override fun insert( contact: UserContactEntity) {
        App.databaseDao.insert(contact)
    }

    override fun updateContact(contact: UserContactEntity) {
        App.databaseDao.updateContact(contact)
    }
}
