package com.triarc.tutorial.android.room_rxjava.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.triarc.tutorial.android.room_rxjava.intf.ItemClickListener
import com.triarc.tutorial.android.room_rxjava.intf.Logger
import com.triarc.tutorial.android.room_rxjava.logger.LoggerImpl

/**
 * Created by Devanshu Verma on 16/1/19
 */
abstract class AbstractBaseRecyclerListAdapter<ViewHolder: AbstractBaseViewHolder>(protected var context: Context? = null): RecyclerView.Adapter<ViewHolder>(), View.OnClickListener {

    protected val logger: Logger = LoggerImpl.getLogger(this)

    companion object ViewType {
        const val ITEM   = 1
        const val FOOTER = 2
    }

    private var itemClickListener: ItemClickListener? = null

    abstract fun getItem(position: Int): Any?

    abstract fun updateList(list: List<Any>?)

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

    abstract override fun getItemCount(): Int

    abstract override fun getItemViewType(position: Int): Int

    abstract override fun onBindViewHolder(viewHolder: ViewHolder, position: Int)

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return getViewHolder(parent, viewType)
    }

    override fun onClick(v: View?) {
        itemClickListener?.onItemClick(v, v?.tag)
    }
}
