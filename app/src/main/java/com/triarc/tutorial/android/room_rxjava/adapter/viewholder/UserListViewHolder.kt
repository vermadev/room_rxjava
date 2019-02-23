package com.triarc.tutorial.android.room_rxjava.adapter.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseViewHolder

/**
 * Created by Devanshu Verma on 16/1/19
 */
class UserListViewHolder(view: View): AbstractBaseViewHolder(view) {
    val email:    TextView = view.findViewById(R.id.email)
    val grade:    TextView = view.findViewById(R.id.grade)
    val school:   TextView = view.findViewById(R.id.school)
    val userName: TextView = view.findViewById(R.id.user_name)

    val delete: ImageView = view.findViewById(R.id.delete)
}
