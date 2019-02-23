package com.triarc.tutorial.android.room_rxjava.logger

import android.util.Log
import com.triarc.tutorial.android.room_rxjava.intf.Logger

/**
 * Created by Devanshu Verma on 13/1/19
 */
class LoggerImpl private constructor(target: Any): Logger {

    private var tag: String? =null

    init {
        tag = target::class.java.simpleName
    }

    companion object {
        @Synchronized
        fun getLogger(target: Any) : Logger {

            return LoggerImpl(target)
        }
    }

    override fun info(logMsg: String) {
        Log.i(tag, logMsg)
    }

    override fun error(logMsg: String) {
        Log.e(tag, logMsg)
    }

    override fun debug(logMsg: String) {
        Log.d(tag, logMsg)
    }
}
