package com.example.contactapplication.data.database

import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.contactapplication.App
import com.example.contactapplication.data.dao.UserContactDao
import com.example.contactapplication.data.entity.UserContactEntity

class DatabaseHelper {

    companion object {
        private var appDatabase = App
            .appContext.let {
                Room.databaseBuilder(
                    it,
                    UsersDatabase::class.java, "mydb"
                )
                    .createFromAsset("databases/mydb.db")
//                .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
        val database: UsersDatabase
            get() = appDatabase

        private val appDatabaseDao = database.userDAO

        val databaseDao: UserContactDao
            get() = appDatabaseDao

        private val appListUserContact = appDatabaseDao.getUsers()

        val listContact: LiveData<MutableList<UserContactEntity>>
            get() = appListUserContact

//        fun setImage(img: Bitmap){
//            val userContactEntity = UserContactEntity()
//            userContactEntity.image = getBytesFromImageMethod(image)
//            dao.updsertByReplacement(userContactEntity)
//
//            fun getImage():Bitmap?{
//                val dao = DatabaseHelper.instance.getImageTestDao()
//                val imageByteArray = dao.getAll()
//                return loadImageFromBytes(imageByteArray[0].data)
//                //change accordingly
//            }
    }

}