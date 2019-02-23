package com.triarc.tutorial.android.room_rxjava.base

import android.content.Context
import androidx.fragment.app.FragmentActivity

/**
 * Created by Devanshu Verma on 13/1/19
 */
interface BaseView {
    fun getContext(): Context?
    fun getActivity(): FragmentActivity?
}
