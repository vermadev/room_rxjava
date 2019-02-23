package com.triarc.tutorial.android.room_rxjava.intf

import android.os.Bundle

/**
 * Created by Devanshu Verma on 23/1/19
 */
interface ActivityCallback {
    fun listener(fragmentName: String?, bundle: Bundle? = null)
}