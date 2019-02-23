package com.triarc.tutorial.android.room_rxjava.repository

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Devanshu Verma on 23/2/19
 */
object Transaction {

    fun insert(completable: Completable): Completable = completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun delete(single: Single<Int>): Single<Int> = single.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun update(completable: Completable): Completable = completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    fun<Type> query(flowable: Flowable<Type>): Flowable<Type> = flowable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}
