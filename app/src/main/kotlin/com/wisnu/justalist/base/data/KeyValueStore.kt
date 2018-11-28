package com.wisnu.justalist.base.data

/**
 * Created by wisnu on 11/27/18
 */
interface KeyValueStore {
    fun putBoolean(key: String, value: Boolean)
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun putInt(key: String, value: Int)
    fun getInt(key: String, defaultValue: Int): Int
    fun putString(key: String, value: String)
    fun getString(key: String, defaultValue: String): String
    fun putFloat(key: String, value: Float)
    fun getFloat(key: String, defaultValue: Float): Float
    fun putLong(key: String, value: Long)
    fun getLong(key: String, defaultValue: Long): Long
    fun putObject(key: String, `object`: Any)
    fun <T> getObject(key: String, defaultValue: Any, tClass: Class<T>): T
    fun putListString(key: String, value: List<String>)
    fun getListString(key: String, defValue: List<String>): List<String>
    fun putListObject(key: String, value: List<Any>)
    fun <T> getListObject(key: String, defValue: List<T>): List<T>
    fun removeKey(key: String)
}