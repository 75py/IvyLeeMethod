package com.nagopy.android.ivyleemethod.data

import androidx.room.RoomDatabase
import com.nagopy.android.ivyleemethod.data.db.TaskDao
import com.nagopy.android.ivyleemethod.data.entity.Task
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.threeten.bp.Instant

class DailyTaskDataRepository(
        private val database: RoomDatabase
        , private val taskDao: TaskDao
) : DailyTaskRepository {

    override fun getDailyTask(date: Instant): Flowable<List<Task>> {
        return taskDao.getDailyTasks(date)
    }

    override fun getUncompletedDailyTasks(date: Instant): Maybe<List<Task>> {
        return taskDao.getUncompletedDailyTasks(date)
    }

    override fun upsert(task: List<Task>): Completable = Completable.create {
        database.runInTransaction {
            taskDao.upsert(task)
            it.onComplete()
        }
    }
}
