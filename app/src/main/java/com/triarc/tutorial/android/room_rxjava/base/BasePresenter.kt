package com.triarc.tutorial.android.room_rxjava.base

/**
 * Created by Devanshu Verma on 13/1/19
 */
interface BasePresenter <View> {
    fun getView(): View?
    fun detachView()
    fun attachView(view: View)
}
