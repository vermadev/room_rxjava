package com.triarc.tutorial.android.room_rxjava.presenter

import android.text.TextUtils
import com.triarc.tutorial.android.room_rxjava.base.AbstractBasePresenter
import com.triarc.tutorial.android.room_rxjava.contract.Book
import com.triarc.tutorial.android.room_rxjava.entity.Book as BookEntity

/**
 * Created by Devanshu Verma on 13/1/19
 */
class BookPresenter(private val model: Book.Model): AbstractBasePresenter<Book.View>(), Book.Presenter  {

    override fun attachView(view: Book.View) {
        super.attachView(view)
        view.onInitialiseView()
        view.onInitialiseAdapter()
        view.onInitialiseListener()
    }

    override fun saveBook(name: String?, author: String?, publication: String?) {
        if(name == null || TextUtils.isEmpty(name))
            getView()?.onInvalidInput("Invalid input, title can not be null")
        else if(author == null || TextUtils.isEmpty(author))
            getView()?.onInvalidInput("Invalid input, author can not be null")
        else if(publication == null || TextUtils.isEmpty(publication))
            getView()?.onInvalidInput("Invalid input, publication can not be null")
        else {
            subscription.add(model.saveBook(BookEntity(name, author, publication)).subscribe({
                getView()?.onBookSaved()
                getExistingBooks()
            }, {
                logger.error("Could't save book ${it.cause}")
            }))
        }
    }

    override fun deleteBook(book: BookEntity?) {
        if(book != null) {
            subscription.add(model.deleteBook(book).subscribe({
                getView()?.onBookDeleted(book)
                getExistingBooks()
            }, {
                logger.error("Could't delete book ${it.cause}")
            }))
        }
    }

    override fun getExistingBooks() {
        subscription.add(model.getExistingBooks().subscribe({books ->
            getView()?.onUpdateView(books)
        }, {
            logger.error("Could't retrieve books from table ${it.cause}")
        }))
    }
}
