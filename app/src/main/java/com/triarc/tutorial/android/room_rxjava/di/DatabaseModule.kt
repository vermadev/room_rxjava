package com.triarc.tutorial.android.room_rxjava.di

import android.app.Application
import androidx.room.Room
import com.triarc.tutorial.android.room_rxjava.storage.dao.UserDao
import com.triarc.tutorial.android.room_rxjava.storage.RoomTutorialDatabase
import com.triarc.tutorial.android.room_rxjava.storage.DatabaseMigrations
import com.triarc.tutorial.android.room_rxjava.storage.dao.BookDao
import com.triarc.tutorial.android.room_rxjava.storage.dao.RegisterDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Devanshu Verma on 14/1/19
 */
@Module
class DatabaseModule {

    object Database {
        const val NAME    = "room_tutorial_database.db"
        const val VERSION = 1
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): RoomTutorialDatabase {
        return Room.databaseBuilder(application, RoomTutorialDatabase::class.java, Database.NAME)
            .addMigrations(*DatabaseMigrations.MIGRATION_LIST.toTypedArray())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: RoomTutorialDatabase): UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideBookDao(database: RoomTutorialDatabase): BookDao = database.bookDao()

    @Provides
    @Singleton
    fun provideRegisterDao(database: RoomTutorialDatabase): RegisterDao = database.registerDao()
}
