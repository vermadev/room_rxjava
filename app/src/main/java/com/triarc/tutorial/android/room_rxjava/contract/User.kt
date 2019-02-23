package com.triarc.tutorial.android.room_rxjava.contract

import com.triarc.tutorial.android.room_rxjava.base.BasePresenter
import com.triarc.tutorial.android.room_rxjava.base.BaseView
import com.triarc.tutorial.android.room_rxjava.entity.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Devanshu Verma on 19/1/19
 */
interface User {
    interface View: BaseView {
        fun onUserSaved()
        fun onUpdateView(users: List<User>?)
        fun onUserDeleted(user: User)
        fun onInvalidInput(message: String)
        fun onInitialiseView()
        fun onInitialiseAdapter()
        fun onInitialiseListener()
    }

    interface Model {
        fun saveUser(user: User): Completable
        fun deleteUser(user: User): Single<Int>
        fun getExistingUsers(): Flowable<List<User>?>
    }

    interface Presenter: BasePresenter<View> {
        fun saveUser(name: String?, email: String?, school: String?, grade: String?)
        fun deleteUser(user: User?)
        fun getExistingUsers()
    }

    interface Repository {
        fun saveUser(user: User): Completable
        fun deleteUser(user: User): Single<Int>
        fun getExistingUsers(): Flowable<List<User>?>
    }
}