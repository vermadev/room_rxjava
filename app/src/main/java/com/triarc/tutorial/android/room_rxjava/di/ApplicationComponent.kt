package com.triarc.tutorial.android.room_rxjava.di

import com.triarc.tutorial.android.room_rxjava.view.*
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Devanshu Verma on 13/1/19
 */
@Singleton
@Component(modules = [HomeModule::class, UserModule::class, BookModule::class, SearchModule::class, DatabaseModule::class, IssueBookModule::class, ApplicationContextModule::class])
interface ApplicationComponent {

    fun inject(target: HomeActivity)
    fun inject(target: UserFragment)
    fun inject(target: BookFragment)
    fun inject(target: SearchFragment)
    fun inject(target: IssueBookFragment)
}
