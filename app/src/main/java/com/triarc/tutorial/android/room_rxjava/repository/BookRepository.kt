package com.triarc.tutorial.android.room_rxjava.repository

import com.triarc.tutorial.android.room_rxjava.contract.Book
import com.triarc.tutorial.android.room_rxjava.storage.dao.BookDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import com.triarc.tutorial.android.room_rxjava.entity.Book as BookEntity

/**
 * Created by Devanshu Verma on 13/1/19
 */
class BookRepository(private val bookDao: BookDao): Book.Repository {

    override fun saveBook(book: BookEntity): Completable = Transaction.insert(bookDao.insert(book))

    override fun deleteBook(book: BookEntity): Single<Int> = Transaction.delete(bookDao.delete(book))

    override fun getExistingBooks(): Flowable<List<BookEntity>?> = Transaction.query(bookDao.getAllBooks())

    override fun getBookByMatchingTitle(name: String): Flowable<List<BookEntity>?> {
        /*val sb = StringBuilder().append("(?:^|.*\\b)(")
        title.toUpperCase().forEach { sb.append(Pattern.quote(it.toString()) + "(?:\\p{Punct}|\\s)*?") }
        sb.append(").*")
        val nameRegex = sb.toString().toRegex()
        return bookDao.getAllBooks()?.filter { book ->
            //if(book == null)
            //    false

            book.title.toUpperCase().matches(nameRegex)
        }*/

        return Transaction.query(bookDao.getBookByMatchingTitle(name))
    }
}
