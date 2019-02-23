package com.triarc.tutorial.android.room_rxjava.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.adapter.viewholder.BookListViewHolder
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseRecyclerListAdapter
import com.triarc.tutorial.android.room_rxjava.entity.Book

/**
 * Created by Devanshu Verma on 16/1/19
 */
class BookListAdapter(private var books: List<Book>? = null): AbstractBaseRecyclerListAdapter<BookListViewHolder>() {

    override fun getItem(position: Int): Any? {
        return if(books != null)
            books!![position]
        else
            null
    }

    override fun getItemCount(): Int = books?.size ?: 0

    @Suppress("UNCHECKED_CAST")
    override fun updateList(list: List<Any>?) {
        if(list != null)
            this.books = list as List<Book>
        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_book, parent, false)
        return BookListViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = if (books?.get(position) != null) ITEM else FOOTER

    override fun onBindViewHolder(viewHolder: BookListViewHolder, position: Int) {
        books?.let {books ->
            viewHolder.bookName.text = books[position].title
            viewHolder.author.text   = books[position].author

            viewHolder.publisher.text = books[position].publication

            viewHolder.delete.tag = position
            viewHolder.delete.setOnClickListener(this)
        }
    }
}
