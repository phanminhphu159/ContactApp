package com.example.contactapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactapplication.data.dao.UserContactDao
import com.example.contactapplication.data.entity.UserContactEntity


@Database(entities = [UserContactEntity::class], version = 2)
abstract class UsersDatabase : RoomDatabase() {
    abstract val userDAO: UserContactDao
}