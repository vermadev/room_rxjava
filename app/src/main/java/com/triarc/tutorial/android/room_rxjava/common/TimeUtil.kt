package com.triarc.tutorial.android.room_rxjava.common

/**
 * Created by Devanshu Verma on 31/1/19
 */
object TimeUtil {
    private const val MIN_IN_MILLI_SEC = 60 * 1000 /*1 minute in milliseconds*/

    fun getDaysInMilliSec(days: Int) = days * getHoursInMilliSec(Constant.HOUR_24)

    fun getHoursInMilliSec(hours: Int) = hours * getMinutesInMilliSec(Constant.MINUTES_60)

    fun getMinutesInMilliSec(minutes: Int) = minutes * MIN_IN_MILLI_SEC

    object Constant {
        const val DAY_1      = 1
        const val DAY_5      = 5
        const val DAY_10     = 10
        const val HOUR_1     = 1
        const val HOUR_24    = 24
        const val MINUTE_1   = 1
        const val MINUTES_5  = 5
        const val MINUTES_10 = 10
        const val MINUTES_60 = 60
    }
}