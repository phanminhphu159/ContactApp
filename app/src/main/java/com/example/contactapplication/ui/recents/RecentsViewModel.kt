package com.example.contactapplication.ui.recents

import android.content.Context
import android.database.Cursor
import android.provider.CallLog
import com.example.contactapplication.base.viewmodel.BaseViewModel
import com.example.contactapplication.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RecentsViewModel @Inject constructor(
    private val appRepository: AppRepository
) : BaseViewModel() {
    val listRecentContacts = appRepository.getRecentContacts()

    fun getCallLogWithDuration(context: Context?) {
        val sb = StringBuffer()
        val contacts = CallLog.Calls.CONTENT_URI
        val managedCursor: Cursor? = context?.contentResolver?.query(contacts, null, null, null, null)
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
                sb.append("\nName:--- $mName\nPhone Number:--- $phNumber \nCall Type:--- $dir \nCall Date:--- $callDayTime \nCall duration in sec :--- $callDuration")
                sb.append("\n----------------------------------")
            }
        }
        managedCursor?.close()
        println(sb)
        Timber.tag("CallLog").i(sb.toString())
    }
}