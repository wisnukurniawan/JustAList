package com.wisnu.justalist.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wisnu.justalist.base.data.KeyValueStore
import org.json.JSONException

/**
 * Created by wisnu on 11/27/18
 */
class SharedPreferenceApi(
    context: Context,
    private val gson: Gson
) : KeyValueStore {
    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    override fun putBoolean(key: String, value: Boolean) {
        editor
            .putBoolean(key, value)
            .apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun putInt(key: String, value: Int) {
        editor
            .putInt(key, value)
            .apply()
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun putString(key: String, value: String) {
        editor
            .putString(key, value)
            .apply()
    }

    override fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: ""
    }

    override fun putFloat(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    override fun putLong(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun putObject(key: String, `object`: Any) {
        val objectString = convertObjectToString(`object`)
        putString(key, objectString)
    }

    override fun <T> getObject(key: String, defaultValue: Any, tClass: Class<T>): T {
        val defValueString = convertObjectToString(defaultValue)
        val jsonValue = sharedPreferences.getString(key, defValueString) ?: ""
        return convertJsonStringToObject(jsonValue, tClass)
    }

    private fun <T> convertJsonStringToObject(jsonValue: String, tClass: Class<T>): T {
        return gson.fromJson(jsonValue, tClass)
    }

    override fun putListString(key: String, value: List<String>) {
        putListObject(key, value)
    }

    @Throws(JSONException::class)
    override fun getListString(key: String, defValue: List<String>): List<String> {
        val defValueString = convertObjectToString(defValue)
        val jsonValue = sharedPreferences.getString(key, defValueString) ?: ""
        return convertJsonStringToListString(jsonValue)
    }

    private fun convertJsonStringToListString(jsonValue: String): List<String> {
        val type = object : TypeToken<List<String>>() {
        }.type
        return gson.fromJson<List<String>>(jsonValue, type)
    }

    override fun putListObject(key: String, value: List<Any>) {
        val jsonValue = convertObjectToString(value)
        editor.putString(key, jsonValue)
        editor.apply()
    }

    @Throws(JSONException::class)
    override fun <T> getListObject(key: String, defValue: List<T>): List<T> {
        val defValueString = convertObjectToString(defValue)
        val jsonValue = sharedPreferences.getString(key, defValueString) ?: ""
        return convertJsonStringToListObject(jsonValue)
    }

    private fun convertObjectToString(value: Any): String {
        return gson.toJson(value)
    }

    @Throws(JSONException::class)
    private fun <T> convertJsonStringToListObject(jsonValue: String): List<T> {
        val typeToken = object : TypeToken<List<T>>() {
        }.type

        return gson.fromJson<List<T>>(jsonValue, typeToken)
    }

    override fun removeKey(key: String) {
        editor.remove(key).apply()
    }

    companion object {
        const val SOURCE_KEY_VALUE = "SOURCE_KEY_VALUE"
    }

}