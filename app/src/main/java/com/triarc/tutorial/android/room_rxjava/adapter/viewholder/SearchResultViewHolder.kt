package com.triarc.tutorial.android.room_rxjava.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseViewHolder

/**
 * Created by Devanshu Verma on 16/1/19
 */
class SearchResultViewHolder(view: View): AbstractBaseViewHolder(view) {
    val author:    TextView = view.findViewById(R.id.author)
    val bookName:  TextView = view.findViewById(R.id.book_name)
    val publisher: TextView = view.findViewById(R.id.publication)
}
