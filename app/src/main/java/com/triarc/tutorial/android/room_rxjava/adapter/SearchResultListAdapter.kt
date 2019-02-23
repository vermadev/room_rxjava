package com.triarc.tutorial.android.room_rxjava.adapter

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import android.text.SpannableStringBuilder
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.adapter.viewholder.SearchResultViewHolder
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseRecyclerListAdapter
import com.triarc.tutorial.android.room_rxjava.entity.Book

/**
 * Created by Devanshu Verma on 24/1/19
 */
class SearchResultListAdapter(context: Context?): AbstractBaseRecyclerListAdapter<SearchResultViewHolder>(context) {

    lateinit var keyword: String

    private var books: List<Book>? = null

    private val boldSpan: CharacterStyle  = StyleSpan(Typeface.BOLD)
    private val colorSpan: CharacterStyle = ForegroundColorSpan(ContextCompat.getColor(context!!, R.color.color_title))

    override fun getItem(position: Int): Any? {
        return if(books != null)
            books!![position]
        else
            null
    }

    override fun getItemCount(): Int = books?.size ?: 0

    @Suppress("UNCHECKED_CAST")
    override fun updateList(list: List<Any>?) {
        books = if(list != null)
            list as List<Book>
        else
            null

        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_search_book, parent, false)
        return SearchResultViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = if (books?.get(position) != null) ITEM else FOOTER

    override fun onBindViewHolder(viewHolder: SearchResultViewHolder, position: Int) {
        books?.let {books ->
            viewHolder.bookName.text = highlightKeyword(books[position].title, keyword)
            viewHolder.author.text   = books[position].author
            viewHolder.publisher.text = books[position].publication

            viewHolder.itemView.tag = position
            viewHolder.itemView.setOnClickListener(this)
        }
    }

    private fun highlightKeyword(bookTitle: String, keyword: String): SpannableStringBuilder {
        val ssb = SpannableStringBuilder(bookTitle)
        val nPos = bookTitle.toLowerCase().indexOf(keyword.toLowerCase())
        if (nPos >= 0) {
            // Set the span color to white.
            val nEnd = nPos + keyword.length
            ssb.setSpan(boldSpan, nPos, nEnd, 0)
            ssb.setSpan(colorSpan, nPos, nEnd, 0)
        }
        return ssb
    }
}