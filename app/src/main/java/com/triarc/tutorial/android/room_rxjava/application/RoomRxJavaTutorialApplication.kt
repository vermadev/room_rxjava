package com.triarc.tutorial.android.room_rxjava.application

import android.app.Application
import com.triarc.tutorial.android.room_rxjava.di.*

/**
 * Created by Devanshu Verma on 13/1/19
 */
class RoomRxJavaTutorialApplication: Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .homeModule(HomeModule())
            .userModule(UserModule())
            .bookModule(BookModule())
            .searchModule(SearchModule())
            .databaseModule(DatabaseModule())
            .issueBookModule(IssueBookModule())
            .applicationContextModule(ApplicationContextModule(this))
            .build()
    }

    fun getApplicationComponent() = applicationComponent
}
