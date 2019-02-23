package com.triarc.tutorial.android.room_rxjava.di

import com.triarc.tutorial.android.room_rxjava.contract.IssueBook
import com.triarc.tutorial.android.room_rxjava.contract.Register
import com.triarc.tutorial.android.room_rxjava.contract.User
import com.triarc.tutorial.android.room_rxjava.model.IssueBookModel
import com.triarc.tutorial.android.room_rxjava.presenter.IssueBookPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Devanshu Verma on 27/1/19
 */
@Module
class IssueBookModule {

    @Provides
    @Singleton
    fun provideIssueBookModel(user: User.Repository, register: Register.Repository): IssueBook.Model = IssueBookModel(user, register)

    @Provides
    @Singleton
    fun provideIssueBookPresenter(model: IssueBook.Model): IssueBook.Presenter = IssueBookPresenter(model)
}
