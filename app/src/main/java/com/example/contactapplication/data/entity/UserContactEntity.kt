package com.example.contactapplication.data.entity
data class UserContactEntity(
    val uid: Int? = null,
    var name: String?,
    var phone: String?,
    var time: String?,
    var status : String?,
    var favorite: Boolean?,
    var image: ByteArray?,
)
