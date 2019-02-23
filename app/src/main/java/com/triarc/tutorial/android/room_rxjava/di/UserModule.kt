package com.triarc.tutorial.android.room_rxjava.di

import com.triarc.tutorial.android.room_rxjava.contract.User
import com.triarc.tutorial.android.room_rxjava.model.UserModel
import com.triarc.tutorial.android.room_rxjava.presenter.UserPresenter
import com.triarc.tutorial.android.room_rxjava.repository.UserRepository
import com.triarc.tutorial.android.room_rxjava.storage.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Devanshu Verma on 13/1/19
 */
@Module
class UserModule {

    @Provides
    @Singleton
    fun provideUserModel(repository: User.Repository): User.Model = UserModel(repository)

    @Provides
    @Singleton
    fun provideUserPresenter(model: User.Model): User.Presenter = UserPresenter(model)

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao): User.Repository = UserRepository(userDao)
}
