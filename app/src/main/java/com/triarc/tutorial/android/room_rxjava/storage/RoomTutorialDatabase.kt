package com.triarc.tutorial.android.room_rxjava.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.triarc.tutorial.android.room_rxjava.di.DatabaseModule
import com.triarc.tutorial.android.room_rxjava.entity.Book
import com.triarc.tutorial.android.room_rxjava.entity.Register
import com.triarc.tutorial.android.room_rxjava.entity.User
import com.triarc.tutorial.android.room_rxjava.storage.dao.BookDao
import com.triarc.tutorial.android.room_rxjava.storage.dao.RegisterDao
import com.triarc.tutorial.android.room_rxjava.storage.dao.UserDao

/**
 * Created by Devanshu Verma on 14/1/19
 */
@Database(entities = [User::class, Book::class, Register::class], version = DatabaseModule.Database.VERSION)
abstract class RoomTutorialDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao
    abstract fun registerDao(): RegisterDao
}
