package com.triarc.tutorial.android.room_rxjava.adapter.viewholder

import android.view.View
import android.widget.TextView
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseViewHolder

/**
 * Created by Devanshu Verma on 16/1/19
 */
class RegisterViewHolder(view: View): AbstractBaseViewHolder(view) {
    val name:      TextView = view.findViewById(R.id.user_name)
    val email:     TextView = view.findViewById(R.id.email)
    val title:     TextView = view.findViewById(R.id.title)
    val author:    TextView = view.findViewById(R.id.author)
    val dueDate:   TextView = view.findViewById(R.id.due_date)
    val issueDate: TextView = view.findViewById(R.id.issue_date)
}