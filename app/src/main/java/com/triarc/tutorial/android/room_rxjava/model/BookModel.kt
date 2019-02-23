package com.triarc.tutorial.android.room_rxjava.model

import com.triarc.tutorial.android.room_rxjava.contract.Book
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.triarc.tutorial.android.room_rxjava.entity.Book as BookEntity

/**
 * Created by Devanshu Verma on 13/1/19
 */
class BookModel(private val repository: Book.Repository): Book.Model {

    override fun saveBook(book: BookEntity): Completable = repository.saveBook(book)

    override fun deleteBook(book: BookEntity): Single<Int> = repository.deleteBook(book)

    override fun getExistingBooks(): Flowable<List<BookEntity>?> = repository.getExistingBooks()

    override fun getBookByMatchingTitle(name: String): Flowable<List<BookEntity>?> = repository.getBookByMatchingTitle(name)
}
