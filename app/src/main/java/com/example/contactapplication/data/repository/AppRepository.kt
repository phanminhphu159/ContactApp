package com.example.contactapplication.data.repository

import android.content.Context
import android.database.Cursor
import android.provider.CallLog
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.contactapplication.data.entity.UserContactEntity
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.List

interface AppRepository {
    fun getContacts(): LiveData<MutableList<UserContactEntity>>
    fun getRecentContacts(): LiveData<MutableList<UserContactEntity>>
    fun getLogContacts(context: Context?): MutableList<UserContactEntity>
    fun insert(contact: UserContactEntity)
    fun updateContact(contact: UserContactEntity)
}

class DatabaseRepository : AppRepository {
//    @WorkerThread
    override fun getContacts(): LiveData<MutableList<UserContactEntity>> {
        val listLogContacts = MutableLiveData<MutableList<UserContactEntity>>()
        listLogContacts.value = mutableListOf()
        return listLogContacts
    }

    override fun getRecentContacts(): LiveData<MutableList<UserContactEntity>> {
        val listLogContacts = MutableLiveData<MutableList<UserContactEntity>>()
        listLogContacts.value = mutableListOf()
        return listLogContacts
    }

    override fun getLogContacts(context: Context?): MutableList<UserContactEntity> {
        val listLogContacts = mutableListOf<UserContactEntity>()
        val sb = StringBuffer()
        val contacts = CallLog.Calls.CONTENT_URI
        val managedCursor: Cursor? =
            context?.contentResolver?.query(contacts, null, null, null, null)
        val name = managedCursor?.getColumnIndex(CallLog.Calls.CACHED_NAME)
        val number = managedCursor?.getColumnIndex(CallLog.Calls.NUMBER)
        val type = managedCursor?.getColumnIndex(CallLog.Calls.TYPE)
        val date = managedCursor?.getColumnIndex(CallLog.Calls.DATE)
        val duration = managedCursor?.getColumnIndex(CallLog.Calls.DURATION)
        sb.append("Call Details :")
        if (managedCursor != null) {
            while (managedCursor.moveToNext()) {
                val rowDataCall = HashMap<String, String>()
                val mName = name?.let { managedCursor.getString(it) }
                val phNumber = number?.let { managedCursor.getString(it) }
                val callType = type?.let { managedCursor.getString(it) }
                val callDate = date?.let { managedCursor.getString(it) }
                val callDayTime =
                    SimpleDateFormat("EEEE dd MMM yyyy HH:mm:s").format(callDate?.let { Date(it.toLong()) })
                // long timestamp = convertDateToTimestamp(callDayTime);
                val callDuration = duration?.let { managedCursor.getString(it) }
                var dir: String? = null
                val dircode = callType?.toInt()
                when (dircode) {
                    CallLog.Calls.OUTGOING_TYPE -> dir = "OUTGOING"
                    CallLog.Calls.INCOMING_TYPE -> dir = "INCOMING"
                    CallLog.Calls.MISSED_TYPE -> dir = "MISSED"
                }
                listLogContacts.add(
                    UserContactEntity(
                        uid = null,
                        name = mName,
                        phone = phNumber,
                        time = callDayTime,
                        favorite = false,
                        status = dir,
                        image = null
                    )
                )
                sb.append("\nName:--- $mName\nPhone Number:--- $phNumber \nCall Type:--- $dir \nCall Date:--- $callDayTime \nCall duration in sec :--- $callDuration")
                sb.append("\n----------------------------------")
            }
        }
        managedCursor?.close()
        println(sb)
        Timber.tag("CallLog").i(sb.toString())

        return listLogContacts
    }

    override fun insert(contact: UserContactEntity) {
//        DatabaseHelper.databaseDao.insert(contact)
    }

    override fun updateContact(contact: UserContactEntity) {
//        DatabaseHelper.databaseDao.updateContact(contact)
    }
}
