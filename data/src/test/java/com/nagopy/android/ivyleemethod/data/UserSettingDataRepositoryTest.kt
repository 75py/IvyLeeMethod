package com.nagopy.android.ivyleemethod.data

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.LocalTime

@RunWith(AndroidJUnit4::class)
class UserSettingDataRepositoryTest {

    lateinit var sharedPreferences: SharedPreferences

    lateinit var userSettingDataRepository: UserSettingDataRepository

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        userSettingDataRepository = UserSettingDataRepository(sharedPreferences)

        sharedPreferences.edit().clear().commit()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun wakeUpTime() {
        assertThat(userSettingDataRepository.wakeUpTime(), `is`(LocalTime.of(6, 0)))

        sharedPreferences.edit()
                .putInt("WakeUpTime", LocalTime.of(10, 30).toSecondOfDay()).commit()

        assertThat(userSettingDataRepository.wakeUpTime(), `is`(LocalTime.of(10, 30)))
    }

    @Test
    fun endOfDay() {
        assertThat(userSettingDataRepository.endOfDay(), `is`(LocalTime.of(21, 0)))

        sharedPreferences.edit()
                .putInt("EndOfDay", LocalTime.of(22, 30).toSecondOfDay()).commit()

        assertThat(userSettingDataRepository.endOfDay(), `is`(LocalTime.of(22, 30)))
    }
}