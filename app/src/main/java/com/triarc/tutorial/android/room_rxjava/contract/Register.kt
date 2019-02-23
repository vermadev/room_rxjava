package com.triarc.tutorial.android.room_rxjava.contract

import android.view.MenuItem
import com.triarc.tutorial.android.room_rxjava.base.BasePresenter
import com.triarc.tutorial.android.room_rxjava.base.BaseView
import com.triarc.tutorial.android.room_rxjava.entity.Entries
import com.triarc.tutorial.android.room_rxjava.entity.Register
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Devanshu Verma on 15/1/19
 */
interface Register {
    interface View: BaseView {
        fun onUpdateView(register: List<Entries>?)
        fun onInitialiseView()
        fun onInitialiseAdapter()
        fun onInitialiseListener()
        fun onOptionsMenuSelected(id: Int)
    }

    interface Model {
        fun getEntriesFromRegister(): Flowable<List<Entries>?>
    }

    interface Presenter: BasePresenter<View> {
        fun updateRegister()
        fun optionsMenuSelected(item: MenuItem?)
    }

    interface Repository {
        fun addEntryToRegister(register: Register): Completable
        fun addEntriesToRegister(entries: List<Register>): Completable
        fun getEntriesFromRegister(): Flowable<List<Entries>?>
        fun deleteEntryFromRegister(register: Register): Single<Int>
        fun updateEntryFromRegister(register: Register): Completable
    }
}