package com.triarc.tutorial.android.room_rxjava.model

import com.triarc.tutorial.android.room_rxjava.contract.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.triarc.tutorial.android.room_rxjava.entity.User as UserEntity

/**
 * Created by Devanshu Verma on 13/1/19
 */
class UserModel(private val repository: User.Repository): User.Model {

    override fun saveUser(user: UserEntity): Completable {
        user.timestamp = System.currentTimeMillis()
        return repository.saveUser(user)
    }

    override fun deleteUser(user: UserEntity): Single<Int> = repository.deleteUser(user)

    override fun getExistingUsers(): Flowable<List<UserEntity>?> = repository.getExistingUsers()
}
