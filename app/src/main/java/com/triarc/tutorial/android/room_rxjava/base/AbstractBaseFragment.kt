package com.triarc.tutorial.android.room_rxjava.base

import android.content.Context
import androidx.fragment.app.Fragment
import android.view.View
import com.triarc.tutorial.android.room_rxjava.intf.Logger
import com.triarc.tutorial.android.room_rxjava.logger.LoggerImpl

/**
 * Created by Devanshu Verma on 15/1/19
 */
abstract class AbstractBaseFragment: Fragment(), BaseView {

    protected val logger: Logger = LoggerImpl.getLogger(this)

    protected var rootView: View? = null

    override fun getContext(): Context? = activity
}
