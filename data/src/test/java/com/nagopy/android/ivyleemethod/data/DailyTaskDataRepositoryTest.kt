package com.nagopy.android.ivyleemethod.data

import androidx.room.RoomDatabase
import com.nagopy.android.ivyleemethod.data.db.TaskDao
import com.nagopy.android.ivyleemethod.data.entity.Task
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.threeten.bp.Instant

class DailyTaskDataRepositoryTest {

    @Mock
    lateinit var database: RoomDatabase

    @Mock
    lateinit var taskDao: TaskDao

    lateinit var dailyTaskDataRepository: DailyTaskDataRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dailyTaskDataRepository = DailyTaskDataRepository(database, taskDao)
    }

    @After
    fun tearDown() {
        reset(database)
        reset(taskDao)
    }

    @Test
    fun getDailyTask() {
        val date = Instant.now()
        @Suppress("UNCHECKED_CAST")
        val resultMock = mock(Flowable::class.java) as Flowable<List<Task>>
        `when`(taskDao.getDailyTasks(date)).thenReturn(resultMock)
        val result = dailyTaskDataRepository.getDailyTask(date)

        assertThat(result, `is`(resultMock))
        verify(taskDao, times(1)).getDailyTasks(date)
    }

    @Test
    fun getUncompletedDailyTasks() {
        val date = Instant.now()
        @Suppress("UNCHECKED_CAST")
        val resultMock = mock(Maybe::class.java) as Maybe<List<Task>>
        `when`(taskDao.getUncompletedDailyTasks(date)).thenReturn(resultMock)
        val result = dailyTaskDataRepository.getUncompletedDailyTasks(date)

        assertThat(result, `is`(resultMock))
        verify(taskDao, times(1)).getUncompletedDailyTasks(date)
    }

    @Test
    fun upsert() {
        // param
        val tasks = listOf(mock(Task::class.java))

        // mock
        `when`(database.runInTransaction(ArgumentMatchers.any(Runnable::class.java))).thenCallRealMethod()
        doNothing().`when`(taskDao).upsert(tasks)

        val result = dailyTaskDataRepository.upsert(tasks)
        result.blockingAwait()
        verify(taskDao, times(1)).upsert(tasks)
    }
}