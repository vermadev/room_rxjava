package com.triarc.tutorial.android.room_rxjava.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.adapter.viewholder.UserListViewHolder
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseRecyclerListAdapter
import com.triarc.tutorial.android.room_rxjava.entity.User

/**
 * Created by Devanshu Verma on 16/1/19
 */
class UserListAdapter(private var users: List<User>? = null): AbstractBaseRecyclerListAdapter<UserListViewHolder>() {

    override fun getItem(position: Int): Any? {
        return if(users != null)
            users!![position]
        else
            null
    }

    override fun getItemCount(): Int = users?.size ?: 0

    @Suppress("UNCHECKED_CAST")
    override fun updateList(list: List<Any>?) {
        if(list != null)
            this.users = list as List<User>
        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = if (users?.get(position) != null) ITEM else FOOTER

    override fun onBindViewHolder(viewHolder: UserListViewHolder, position: Int) {
        users?.let {users ->
            viewHolder.userName.text = users[position].name
            viewHolder.email.text    = users[position].email

            viewHolder.school.text = "${users[position].school},"
            viewHolder.grade.text  = "Grade: ${users[position].grade}"

            viewHolder.delete.tag = position
            viewHolder.delete.setOnClickListener(this)
        }
    }
}
