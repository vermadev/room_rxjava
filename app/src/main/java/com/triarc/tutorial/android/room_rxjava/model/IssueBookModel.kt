package com.triarc.tutorial.android.room_rxjava.model

import com.triarc.tutorial.android.room_rxjava.contract.IssueBook
import com.triarc.tutorial.android.room_rxjava.contract.User
import com.triarc.tutorial.android.room_rxjava.contract.Register
import com.triarc.tutorial.android.room_rxjava.entity.Entries
import io.reactivex.Completable
import io.reactivex.Flowable
import com.triarc.tutorial.android.room_rxjava.entity.Register as RegisterEntity
import com.triarc.tutorial.android.room_rxjava.entity.User as UserEntity

/**
 * Created by Devanshu Verma on 30/1/19
 */
class IssueBookModel(private val user: User.Repository, private val register: Register.Repository): IssueBook.Model {

    override fun getUsers(): Flowable<List<UserEntity>?> = user.getExistingUsers()

    override fun addEntriesToRegister(entries: List<RegisterEntity>): Completable = register.addEntriesToRegister(entries)

    override fun getEntriesFromRegister():Flowable<List<Entries>?> = register.getEntriesFromRegister()
}
