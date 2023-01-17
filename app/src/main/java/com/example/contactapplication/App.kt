package com.example.contactapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.contactapplication.data.dao.UserContactDao
import com.example.contactapplication.data.database.UsersDatabase
import com.example.contactapplication.data.entity.UserContactEntity

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
        applistUserContact = appDatabaseDao.getUsers()

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

        private lateinit var applistUserContact: List<UserContactEntity>
        val listContact: List<UserContactEntity>
            get() = applistUserContact

    }
}
