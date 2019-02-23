package com.triarc.tutorial.android.room_rxjava.di

import com.triarc.tutorial.android.room_rxjava.contract.Book
import com.triarc.tutorial.android.room_rxjava.contract.User
import com.triarc.tutorial.android.room_rxjava.model.BookModel
import com.triarc.tutorial.android.room_rxjava.model.UserModel
import com.triarc.tutorial.android.room_rxjava.presenter.BookPresenter
import com.triarc.tutorial.android.room_rxjava.presenter.UserPresenter
import com.triarc.tutorial.android.room_rxjava.repository.BookRepository
import com.triarc.tutorial.android.room_rxjava.repository.UserRepository
import com.triarc.tutorial.android.room_rxjava.storage.dao.BookDao
import com.triarc.tutorial.android.room_rxjava.storage.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Devanshu Verma on 13/1/19
 */
@Module
class BookModule {

    @Provides
    @Singleton
    fun provideUserModel(repository: Book.Repository): Book.Model = BookModel(repository)

    @Provides
    @Singleton
    fun provideUserPresenter(model: Book.Model): Book.Presenter = BookPresenter(model)

    @Provides
    @Singleton
    fun provideUserRepository(bookDao: BookDao): Book.Repository = BookRepository(bookDao)
}
