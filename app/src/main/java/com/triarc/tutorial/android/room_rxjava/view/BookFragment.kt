package com.triarc.tutorial.android.room_rxjava.view

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.adapter.BookListAdapter
import com.triarc.tutorial.android.room_rxjava.application.RoomRxJavaTutorialApplication
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseFragment
import com.triarc.tutorial.android.room_rxjava.contract.Book
import com.triarc.tutorial.android.room_rxjava.intf.ItemClickListener
import kotlinx.android.synthetic.main.fragment_book.*
import javax.inject.Inject
import com.triarc.tutorial.android.room_rxjava.entity.Book as BookEntity

/**
 * Created by Devanshu Verma on 15/1/19
 */
class BookFragment: AbstractBaseFragment(), Book.View, ItemClickListener {

    private var bookListAdapter: BookListAdapter? = null

    private var recyclerLayoutManager: RecyclerView.LayoutManager? = null

    @Inject
    lateinit var presenter: Book.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ((activity as? BaseFragmentActivity)?.application as? RoomRxJavaTutorialApplication)?.getApplicationComponent()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        rootView = inflater.inflate(R.layout.fragment_book, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getExistingBooks()
    }

    override fun onBookSaved() {
        book_name.text?.clear()
        author.text?.clear()
        publication.text?.clear()
    }

    override fun onBookDeleted(book: BookEntity) {
        Toast.makeText(context, "Book '${book.title}' Deleted successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onUpdateView(books: List<BookEntity>?) {
        swipe_container.isRefreshing = false
        bookListAdapter?.updateList(books)
    }

    override fun onInvalidInput(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onInitialiseView() {
        book_recycler.isNestedScrollingEnabled = false

        recyclerLayoutManager = LinearLayoutManager(context)
        book_recycler.layoutManager = recyclerLayoutManager
        book_recycler.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
   }

    override fun onInitialiseAdapter() {
        bookListAdapter = BookListAdapter()
        bookListAdapter?.setItemClickListener(this)

        book_recycler.adapter = bookListAdapter
    }

    override fun onInitialiseListener() {
        swipe_container.setOnRefreshListener {
            presenter.getExistingBooks()
        }

        save.setOnClickListener {
            presenter.saveBook(book_name.text?.toString(), author.text?.toString(), publication.text?.toString())
        }
    }

    override fun onItemClick(view: View?, tag: Any?) {
        if(tag != null) {
            presenter.deleteBook(bookListAdapter?.getItem(tag as Int) as? BookEntity)
        }
    }
}
