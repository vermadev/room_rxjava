package com.triarc.tutorial.android.room_rxjava.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.adapter.viewholder.RegisterViewHolder
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseRecyclerListAdapter
import com.triarc.tutorial.android.room_rxjava.entity.Entries
import java.util.*

/**
 * Created by Devanshu Verma on 16/1/19
 */
class RegisterListAdapter(private var registerEntries: List<Entries>? = null): AbstractBaseRecyclerListAdapter<RegisterViewHolder>() {

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemCount(): Int = registerEntries?.size ?: 0

    @Suppress("UNCHECKED_CAST")
    override fun updateList(list: List<Any>?) {
        registerEntries = if(list != null)
                list as List<Entries>
            else
                null

        notifyDataSetChanged()
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RegisterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_register_entry, parent, false)
        return RegisterViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = if (registerEntries?.get(position) != null) ITEM else FOOTER

    override fun onBindViewHolder(viewHolder: RegisterViewHolder, position: Int) {
        val entry = registerEntries?.get(position)
        entry?.let {
            with(it) {
                viewHolder.name.text   = name
                viewHolder.email.text  = email
                viewHolder.title.text  = title
                viewHolder.author.text = author

                val dueOn = Calendar.getInstance()
                dueOn.timeInMillis = dueDate
                viewHolder.dueDate.text = "${dueOn.get(Calendar.DATE)}-${dueOn.get(Calendar.MONTH)+1}-${dueOn.get(Calendar.YEAR)}"

                val issueOn = Calendar.getInstance()
                issueOn.timeInMillis = issueDate
                viewHolder.issueDate.text = "${issueOn.get(Calendar.DATE)}-${issueOn.get(Calendar.MONTH)+1}-${issueOn.get(Calendar.YEAR)}"
            }
        }
    }
}
