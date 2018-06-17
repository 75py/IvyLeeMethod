package com.nagopy.android.ivyleemethod.data.db

import androidx.annotation.CheckResult
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nagopy.android.ivyleemethod.data.entity.Task
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.threeten.bp.Instant

@Dao
abstract class TaskDao {

    @CheckResult
    @Query("select * from task where date = :date order by priority")
    abstract fun getDailyTasks(date: Instant): Flowable<List<Task>>

    @CheckResult
    @Query("select * from task where date = :date order by priority")
    abstract fun getDailyTasksOnce(date: Instant): Maybe<List<Task>>

    @CheckResult
    @Query("select * from task where date = :date and isCompleted = 0 order by priority")
    abstract fun getUncompletedDailyTasks(date: Instant): Maybe<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun upsert(task: List<Task>)

}
