package com.wisnu.justalist.base

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by wisnu on 11/28/18
 */
abstract class BaseRecyclerAdapter<VH, T> : RecyclerView.Adapter<VH>() where VH : RecyclerView.ViewHolder {
    abstract fun getDataAtPosition(position: Int): T
    abstract fun getData(): MutableList<T>
    abstract fun setData(data: MutableList<T>)
    abstract fun addAllData(data: MutableList<T>)
    abstract fun addData(data: T)
    abstract fun clearData()
}