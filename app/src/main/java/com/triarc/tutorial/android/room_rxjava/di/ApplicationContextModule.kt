package com.triarc.tutorial.android.room_rxjava.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Devanshu Verma on 13/1/19
 */
@Module
class ApplicationContextModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application.baseContext

    @Provides
    @Singleton
    fun provideApplication(): Application = application
}
