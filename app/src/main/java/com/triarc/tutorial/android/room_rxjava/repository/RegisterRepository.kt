package com.triarc.tutorial.android.room_rxjava.repository

import com.triarc.tutorial.android.room_rxjava.contract.Register
import com.triarc.tutorial.android.room_rxjava.entity.Entries
import com.triarc.tutorial.android.room_rxjava.storage.dao.RegisterDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.triarc.tutorial.android.room_rxjava.entity.Register as RegisterEntity

/**
 * Created by Devanshu Verma on 16/1/19
 */
class RegisterRepository(private val registerDao: RegisterDao): Register.Repository {

    override fun addEntryToRegister(register: RegisterEntity): Completable = Transaction.insert(registerDao.insert(register))

    override fun addEntriesToRegister(entries: List<RegisterEntity>): Completable = Transaction.insert(registerDao.insert(*entries.toTypedArray()))

    override fun getEntriesFromRegister(): Flowable<List<Entries>?> = Transaction.query(registerDao.getRegisterEntries())

    override fun deleteEntryFromRegister(register: RegisterEntity): Single<Int> = Transaction.delete(registerDao.delete(register))

    override fun updateEntryFromRegister(register: RegisterEntity): Completable = Transaction.update(registerDao.update(register))
}
