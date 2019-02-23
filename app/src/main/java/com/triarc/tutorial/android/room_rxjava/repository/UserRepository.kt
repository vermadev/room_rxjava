package com.triarc.tutorial.android.room_rxjava.repository

import com.triarc.tutorial.android.room_rxjava.contract.User
import com.triarc.tutorial.android.room_rxjava.storage.dao.UserDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.triarc.tutorial.android.room_rxjava.entity.User as UserEntity

/**
 * Created by Devanshu Verma on 13/1/19
 */
class UserRepository(private val userDao: UserDao): User.Repository {

    override fun saveUser(user: UserEntity): Completable = Transaction.insert(userDao.insert(user))

    override fun deleteUser(user: UserEntity): Single<Int> = Transaction.delete(userDao.delete(user))

    override fun getExistingUsers(): Flowable<List<UserEntity>?> = Transaction.query(userDao.getAllUsers())
}
