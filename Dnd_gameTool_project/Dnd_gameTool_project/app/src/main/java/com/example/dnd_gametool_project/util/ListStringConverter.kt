package com.example.dnd_gametool_project.util

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken




class ListStringConverter {
/*
        @TypeConverter
        fun toListOfStrings(flatStringList: String?): MutableList<String>? {
            return flatStringList?.split(",")?.toMutableList()
        }

        @TypeConverter
        fun fromListOfStrings(listOfString: MutableList<String>?): String? {
            return listOfString?.joinToString(",")
        }*/
    @TypeConverter
    fun restoreList(listOfString: String?): MutableList<String?>? {
        val gson = Gson()
        return gson.fromJson<List<String>>(listOfString, object: TypeToken<List<String?>?>() {}.type).toMutableList()
    }

    @TypeConverter
    fun saveList(listOfString: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(listOfString)
    }
}