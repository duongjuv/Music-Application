package com.example.musicapplication.data.source.local

import androidx.room.TypeConverter
import java.util.Date

object DateConverter {

    @TypeConverter
    fun toDate(millis: Long): Date {
        return Date(millis)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long {
        return date?.time ?: 0
    }

}