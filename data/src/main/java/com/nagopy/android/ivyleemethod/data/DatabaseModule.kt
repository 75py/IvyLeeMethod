package com.nagopy.android.ivyleemethod.data

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.nagopy.android.ivyleemethod.data.db.AppDatabase
import com.nagopy.android.ivyleemethod.data.db.TaskDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open
class DatabaseModule {
    companion object {
        val instance = DatabaseModule()
    }

    @Singleton
    @Provides
    fun provideDailyTaskRepository(database: AppDatabase, taskDao: TaskDao)
            : DailyTaskRepository = DailyTaskDataRepository(database, taskDao)

    @Singleton
    @Provides
    fun provideUserSettingRepository(sharedPreferences: SharedPreferences)
            : UserSettingRepository = UserSettingDataRepository(sharedPreferences)

    @Singleton
    @Provides
    open fun provideDb(app: Application): AppDatabase =
            Room.databaseBuilder(app, AppDatabase::class.java, "db")
                    //.allowMainThreadQueries()
                    .build()

    @Singleton
    @Provides
    fun provideDailyTaskDao(db: AppDatabase): TaskDao = db.dailyTaskDao()
}
