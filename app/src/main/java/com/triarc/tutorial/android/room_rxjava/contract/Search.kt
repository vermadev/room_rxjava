package com.triarc.tutorial.android.room_rxjava.contract

import com.triarc.tutorial.android.room_rxjava.base.BasePresenter
import com.triarc.tutorial.android.room_rxjava.base.BaseView
import com.triarc.tutorial.android.room_rxjava.entity.Book

/**
 * Created by Devanshu Verma on 24/1/19
 */
interface Search {
    interface View: BaseView {
        fun onHideKeyBoard()
        fun onFilterUpdated(constraint: String, length: Int)
        fun onRequestKeyBoard()
        fun onInitialiseLayout()
        fun onInitialiseAdapter()
        fun onInitialiseListener()
        fun onBooksByMatchingTitle(books: List<Book>?)
    }

    interface Presenter: BasePresenter<View> {
        fun postEvent(event: String, book: Book)
        fun hideKeyBoard()
        fun registerEventBus()
        fun unRegisterEventBus()
        fun updateSearchScreen(constraint: String)
        fun getBookByMatchingTitle(constraint: String)
    }
}
