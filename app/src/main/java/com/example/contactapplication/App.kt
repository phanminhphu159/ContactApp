package com.example.contactapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.contactapplication.data.dao.UserContactDao
import com.example.contactapplication.data.database.UsersDatabase
import com.example.contactapplication.data.entity.UserContactEntity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        appDatabase = appContext.let {
            Room.databaseBuilder(
                it,
                UsersDatabase::class.java, "mydb"
            )
                .createFromAsset("databases/mydb.db")
//                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
        appDatabaseDao = database.userDAO
        appListUserContact = appDatabaseDao.getUsers()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context
        val appContext: Context
            get() = context

        private lateinit var appDatabase: UsersDatabase
        val database: UsersDatabase
            get() = appDatabase

        private lateinit var appDatabaseDao: UserContactDao
        val databaseDao: UserContactDao
            get() = appDatabaseDao

        private lateinit var appListUserContact: LiveData<MutableList<UserContactEntity>>
        val listContact: LiveData<MutableList<UserContactEntity>>
            get() = appListUserContact

    }
}
