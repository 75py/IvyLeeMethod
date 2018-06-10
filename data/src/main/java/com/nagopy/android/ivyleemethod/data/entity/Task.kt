package com.nagopy.android.ivyleemethod.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import org.threeten.bp.Instant

@Entity(tableName = "task"
        , indices = [Index("date", "priority", unique = true)]
)
data class Task(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0
        , var date: Instant
        , var priority: Int
        , var mission: String
        , var isCompleted: Boolean = false
)
