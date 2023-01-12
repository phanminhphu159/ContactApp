package com.example.contactapplication.ui.contacts.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.contactapplication.R
import android.widget.TextView
import java.util.ArrayList

class SimpleRecyclerView : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    StickHeaderItemDecoration.StickyHeaderInterface {
    private val mData: MutableList<Data>

    init {
        mData = ArrayList()
        mData.add(Data(1))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(2))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(1))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(2))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
        mData.add(Data(0))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> return HeaderViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_contact, parent, false)
            )
            2 -> return Header2ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_contact, parent, false)
            )
            else -> { // Note the block
                return ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_user_contact, parent, false)
                )
            }
        }
//        return ViewHolder(
//            LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_user_contact, parent, false)
//        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bindData(position)
        } else if (holder is HeaderViewHolder) {
            holder.bindData(position)
        } else if (holder is Header2ViewHolder) {
            holder.bindData(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return mData[position].viewType
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var itemPosition = itemPosition
        var headerPosition = 0
        do {
            if (isHeader(itemPosition)) {
                headerPosition = itemPosition
                break
            }
            itemPosition -= 1
        } while (itemPosition >= 0)
        return headerPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return if (mData[headerPosition].viewType == 1) R.layout.item_user_contact_header else {
            R.layout.item_user_contact_header
        }
    }

    override fun bindHeaderData(header: View?, headerPosition: Int) {}

    override fun isHeader(itemPosition: Int): Boolean {
        return mData[itemPosition].viewType == 1 || mData[itemPosition].viewType == 2
    }

    internal inner class HeaderViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var tvHeader: TextView? = null
        fun bindData(position: Int) {
            tvHeader?.text = (position / 5).toString()
        }
    }

    internal inner class Header2ViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var tvHeader: TextView? = null
        fun bindData(position: Int) {
            tvHeader?.text = (position / 5).toString()
        }
    }

    internal inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var tvRows: TextView? = null
        fun bindData(position: Int) {
            tvRows?.text = "saber$position"
//            (tvRows?.parent as ViewGroup).setBackgroundColor(Color.parseColor("#ffffff"))
        }
    }

    internal inner class Data(var viewType: Int)
}