package com.triarc.tutorial.android.room_rxjava.contract

import android.view.MenuItem
import com.triarc.tutorial.android.room_rxjava.base.BasePresenter
import com.triarc.tutorial.android.room_rxjava.base.BaseView
import com.triarc.tutorial.android.room_rxjava.entity.Book
import com.triarc.tutorial.android.room_rxjava.entity.Entries
import com.triarc.tutorial.android.room_rxjava.entity.Register
import com.triarc.tutorial.android.room_rxjava.entity.User
import com.triarc.tutorial.android.room_rxjava.ipc.EventBus
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Devanshu Verma on 21/1/19
 */
interface IssueBook {
    interface View: BaseView {
        fun onBookSelected(book: Book, users: List<User>?)
        fun onInitialiseView()
        fun onInitialiseListener()
        fun onOptionsMenuSelected(id: Int)
    }

    interface Model {
        fun getUsers(): Flowable<List<User>?>
        fun addEntriesToRegister(entries: List<Register>): Completable
        fun getEntriesFromRegister(): Flowable<List<Entries>?>
    }

    interface Presenter: BasePresenter<View> {
        fun selectBook(book: Book)
        fun registerEventBus(listener: EventBus.Listener, vararg events: String)
        fun unRegisterEventBus()
        fun optionsMenuSelected(item: MenuItem?)
        fun addEntriesToRegister(entries: List<Register>)
    }
}
