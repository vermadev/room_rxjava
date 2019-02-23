package com.triarc.tutorial.android.room_rxjava.di

import com.triarc.tutorial.android.room_rxjava.contract.Register
import com.triarc.tutorial.android.room_rxjava.model.RegisterModel
import com.triarc.tutorial.android.room_rxjava.presenter.RegisterPresenter
import com.triarc.tutorial.android.room_rxjava.repository.RegisterRepository
import com.triarc.tutorial.android.room_rxjava.storage.dao.RegisterDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Devanshu Verma on 16/1/19
 */
@Module
class HomeModule {

    @Provides
    @Singleton
    fun provideHomeModel(repository: Register.Repository): Register.Model = RegisterModel(repository)

    @Provides
    @Singleton
    fun provideHomePresenter(model: Register.Model): Register.Presenter = RegisterPresenter(model)

    @Provides
    @Singleton
    fun provideHomeRepository(registerDao: RegisterDao): Register.Repository = RegisterRepository(registerDao)
}
