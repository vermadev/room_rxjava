package com.triarc.tutorial.android.room_rxjava.contract

import com.triarc.tutorial.android.room_rxjava.base.BasePresenter
import com.triarc.tutorial.android.room_rxjava.base.BaseView
import com.triarc.tutorial.android.room_rxjava.entity.Book
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Devanshu Verma on 19/1/19
 */
interface Book {
    interface View: BaseView {
        fun onBookSaved()
        fun onUpdateView(books: List<Book>?)
        fun onBookDeleted(book: Book)
        fun onInvalidInput(message: String)
        fun onInitialiseView()
        fun onInitialiseAdapter()
        fun onInitialiseListener()
    }

    interface Model {
        fun saveBook(book: Book): Completable
        fun deleteBook(book: Book): Single<Int>
        fun getExistingBooks(): Flowable<List<Book>?>
        fun getBookByMatchingTitle(name: String): Flowable<List<Book>?>
    }

    interface Presenter: BasePresenter<View> {
        fun saveBook(name: String?, author: String?, publication: String?)
        fun deleteBook(book: Book?)
        fun getExistingBooks()
    }

    interface Repository {
        fun saveBook(book: Book): Completable
        fun deleteBook(book: Book): Single<Int>
        fun getExistingBooks(): Flowable<List<Book>?>
        fun getBookByMatchingTitle(name: String): Flowable<List<Book>?>
    }
}
