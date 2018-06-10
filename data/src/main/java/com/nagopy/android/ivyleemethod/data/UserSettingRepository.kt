package com.nagopy.android.ivyleemethod.data

import org.threeten.bp.LocalTime

interface UserSettingRepository {

    fun wakeUpTime(): LocalTime

    fun endOfDay(): LocalTime

}
