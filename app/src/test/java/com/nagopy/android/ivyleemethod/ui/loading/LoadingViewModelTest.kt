package com.nagopy.android.ivyleemethod.ui.loading

import androidx.navigation.NavController
import com.nagopy.android.ivyleemethod.R
import com.nagopy.android.ivyleemethod.data.DailyTaskRepository
import com.nagopy.android.ivyleemethod.data.entity.Task
import com.nagopy.android.ivyleemethod.rx.SchedulerProvider
import io.reactivex.Maybe
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.threeten.bp.Instant
import org.threeten.bp.temporal.ChronoUnit
import java.lang.RuntimeException

class LoadingViewModelTest {

    lateinit var loadingViewModel: LoadingViewModel

    lateinit var uiScheduler: TestScheduler
    lateinit var computationException: TestScheduler

    @Mock
    lateinit var dailyTaskRepository: DailyTaskRepository

    @Mock
    lateinit var schedulerProvider: SchedulerProvider

    @Mock
    lateinit var navController: NavController

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        uiScheduler = TestScheduler()
        `when`(schedulerProvider.ui()).thenReturn(uiScheduler)
        computationException = TestScheduler()
        `when`(schedulerProvider.computation()).thenReturn(computationException)

        loadingViewModel = LoadingViewModel(dailyTaskRepository, schedulerProvider)
    }

    @After
    fun tearDown() {
        reset(dailyTaskRepository)
        reset(schedulerProvider)
        reset(navController)
    }

    @Test
    fun loadTodaysTasks_hasUncompletedTasks() {
        val tasks = listOf(
                Task(date = Instant.now(), priority = 1, mission = "test1", isCompleted = false)
                , Task(date = Instant.now(), priority = 2, mission = "test2", isCompleted = false)
        )
        val maybe = Maybe.just(tasks)
        `when`(dailyTaskRepository.getDailyTasksOnce(Instant.now().truncatedTo(ChronoUnit.DAYS)))
                .thenReturn(maybe)

        loadingViewModel.loadTodaysTasks(navController)

        computationException.triggerActions()
        uiScheduler.triggerActions()

        verify(navController, times(1)).navigate(R.id.action_loadingFragment_to_currentTaskFragment)
        verify(navController, never()).navigate(R.id.action_loadingFragment_to_writeTasksFragment)
    }

    @Test
    fun loadTodaysTasks_noTasks() {
        val tasks = emptyList<Task>()
        val maybe = Maybe.just(tasks)
        `when`(dailyTaskRepository.getDailyTasksOnce(Instant.now().truncatedTo(ChronoUnit.DAYS)))
                .thenReturn(maybe)

        loadingViewModel.loadTodaysTasks(navController)

        computationException.triggerActions()
        uiScheduler.triggerActions()

        verify(navController, never()).navigate(R.id.action_loadingFragment_to_currentTaskFragment)
        verify(navController, times(1)).navigate(R.id.action_loadingFragment_to_writeTasksFragment)
    }

    @Test
    fun loadTodaysTasks_failure() {
        val maybe = Maybe.error<List<Task>>(RuntimeException())
        `when`(dailyTaskRepository.getDailyTasksOnce(Instant.now().truncatedTo(ChronoUnit.DAYS)))
                .thenReturn(maybe)

        loadingViewModel.loadTodaysTasks(navController)

        computationException.triggerActions()
        uiScheduler.triggerActions()

        verify(navController, never()).navigate(R.id.action_loadingFragment_to_currentTaskFragment)
        verify(navController, never()).navigate(R.id.action_loadingFragment_to_writeTasksFragment)
    }

}
