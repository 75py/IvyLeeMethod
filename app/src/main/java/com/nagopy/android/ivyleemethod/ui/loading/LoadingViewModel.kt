package com.nagopy.android.ivyleemethod.ui.loading

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.nagopy.android.ivyleemethod.R
import com.nagopy.android.ivyleemethod.data.DailyTaskRepository
import com.nagopy.android.ivyleemethod.rx.Result
import com.nagopy.android.ivyleemethod.rx.SchedulerProvider
import com.nagopy.android.ivyleemethod.rx.toResult
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.threeten.bp.Instant
import org.threeten.bp.temporal.ChronoUnit
import timber.log.Timber
import javax.inject.Inject

class LoadingViewModel @Inject constructor(
        val dailyTaskRepository: DailyTaskRepository
        , val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun loadTodaysTasks(navController: NavController) {
        dailyTaskRepository.getDailyTasksOnce(Instant.now().truncatedTo(ChronoUnit.DAYS))
                .toResult(schedulerProvider)
                .subscribeOn(schedulerProvider.computation())
                .subscribe { result ->
                    when (result) {
                        is Result.Success -> {
                            Timber.d("SUCCESS %s", result.data)
                            if (result.data.isEmpty()) {
                                navController.navigate(R.id.action_loadingFragment_to_writeTasksFragment)
                            } else if (result.data.any { !it.isCompleted }) {
                                navController.navigate(R.id.action_loadingFragment_to_currentTaskFragment)
                            } else {
                                // TODO Congratulations!
                            }
                        }
                        is Result.Failure -> {
                            Timber.d(result.e)
                        }
                        is Result.InProgress -> {
                            Timber.d("InProgress")
                        }
                    }
                }
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}
