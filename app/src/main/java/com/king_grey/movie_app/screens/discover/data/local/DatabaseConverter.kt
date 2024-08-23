package com.king_grey.movie_app.screens.discover.data.local

import androidx.room.TypeConverter

class DatabaseConverter {

    private val separator = ","

    // Convert List<Integer> to a comma-separated String
    @TypeConverter
    fun convertIntegerListToString(list: List<Int>?): String? {
        return list?.joinToString(separator)
    }

    // Convert a comma-separated String to List<Integer>
    @TypeConverter
    fun convertStringToIntegerList(string: String?): List<Int> {
        return string?.split(separator)?.mapNotNull {
            it.toIntOrNull()
        } ?: emptyList()
    }

    // Convert List<String> to a comma-separated String
    @TypeConverter
    fun convertStringListToString(list: List<String>?): String? {
        return list?.joinToString(separator)
    }

    // Convert a comma-separated String to List<String>
    @TypeConverter
    fun convertStringToStringList(string: String?): List<String> {
        return string?.split(separator) ?: emptyList()
    }
}
