package com.example.contactapplication.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.contactapplication.data.database.DatabaseHelper
import com.example.contactapplication.data.entity.UserContactEntity

interface AppRepository {
    fun getContacts(): LiveData<MutableList<UserContactEntity>>
    fun getRecentContacts(): LiveData<MutableList<UserContactEntity>>
    fun getLogContacts(): LiveData<MutableList<UserContactEntity>>
    fun insert( contact: UserContactEntity)
    fun updateContact( contact: UserContactEntity)
}

class DatabaseRepository : AppRepository {
    @WorkerThread
    override fun getContacts(): LiveData<MutableList<UserContactEntity>> {
        return DatabaseHelper.listContact
    }

    override fun getRecentContacts(): LiveData<MutableList<UserContactEntity>> {
        return DatabaseHelper.listContact
    }

    override fun getLogContacts(): LiveData<MutableList<UserContactEntity>> {
        TODO("Not yet implemented")
    }

    override fun insert( contact: UserContactEntity) {
        DatabaseHelper.databaseDao.insert(contact)
    }

    override fun updateContact(contact: UserContactEntity) {
        DatabaseHelper.databaseDao.updateContact(contact)
    }
}
