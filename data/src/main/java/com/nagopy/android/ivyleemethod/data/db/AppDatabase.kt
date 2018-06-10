package com.nagopy.android.ivyleemethod.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nagopy.android.ivyleemethod.data.entity.Task

@Database(entities = [
    Task::class
], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dailyTaskDao(): TaskDao
}
