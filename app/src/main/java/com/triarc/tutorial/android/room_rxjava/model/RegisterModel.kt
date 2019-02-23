package com.triarc.tutorial.android.room_rxjava.model

import com.triarc.tutorial.android.room_rxjava.contract.Register
import com.triarc.tutorial.android.room_rxjava.entity.Entries
import io.reactivex.Flowable

/**
 * Created by Devanshu Verma on 16/1/19
 */
class RegisterModel(private val repository: Register.Repository): Register.Model {

    override fun getEntriesFromRegister(): Flowable<List<Entries>?> = repository.getEntriesFromRegister()
}