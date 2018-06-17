package com.nagopy.android.ivyleemethod.data

import com.nagopy.android.ivyleemethod.data.entity.Task
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.threeten.bp.Instant

interface DailyTaskRepository {

    fun getDailyTask(date: Instant): Flowable<List<Task>>

    fun getDailyTasksOnce(date: Instant): Maybe<List<Task>>

    fun getUncompletedDailyTasks(date: Instant): Maybe<List<Task>>

    fun upsert(task: List<Task>): Completable
}
