package com.example.contactapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserContactEntity(
    @PrimaryKey(autoGenerate = true) val uid: Int? = null,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "phone") var phone: String?,
    @ColumnInfo(name = "favorite") var favorite: Boolean?,
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB) var image: ByteArray?
)
