package com.example.contactapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.contactapplication.data.entity.UserContactEntity


@Dao
interface UserContactDao {
//    @Query("SELECT * FROM users")
//    fun getAll(): List<UserContactEntity>
//
//    @Query("SELECT * FROM users WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<UserContactEntity>
//
//    @Query("SELECT * FROM users WHERE name LIKE :first AND " +
//            "phone LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): UserContactEntity

//    @Insert
//    fun insertAll(vararg users: UserContactEntity)
//
//    @Update
//    fun updateContact(vararg items: UserContactEntity?)
//
//    @Delete
//    fun delete(item: UserContactEntity?)
//
//    @Query("DELETE FROM users")
//    fun deleteAll()
//
//    @Query("SELECT * FROM users WHERE uid = :id")
//    fun getItemById(id: Long?): UserContactEntity?

    @Update
    fun updateContact(item: UserContactEntity)

    @Insert
    fun insert(vararg users: UserContactEntity)

    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<MutableList<UserContactEntity>>

}