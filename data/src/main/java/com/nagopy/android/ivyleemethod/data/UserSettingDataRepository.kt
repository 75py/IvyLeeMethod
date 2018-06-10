package com.nagopy.android.ivyleemethod.data

import android.content.SharedPreferences
import org.threeten.bp.LocalTime

class UserSettingDataRepository(
        val sharedPreferences: SharedPreferences
) : UserSettingRepository {

    override fun wakeUpTime(): LocalTime {
        val sec = sharedPreferences.getInt("WakeUpTime", LocalTime.of(6, 0).toSecondOfDay())
        return LocalTime.ofSecondOfDay(sec.toLong())
    }

    override fun endOfDay(): LocalTime {
        val sec = sharedPreferences.getInt("EndOfDay", LocalTime.of(21, 0).toSecondOfDay())
        return LocalTime.ofSecondOfDay(sec.toLong())
    }
}
