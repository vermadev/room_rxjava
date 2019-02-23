package com.triarc.tutorial.android.room_rxjava.presenter

import android.os.Bundle
import com.triarc.tutorial.android.room_rxjava.base.AbstractBasePresenter
import com.triarc.tutorial.android.room_rxjava.common.IntentKey
import com.triarc.tutorial.android.room_rxjava.contract.Book
import com.triarc.tutorial.android.room_rxjava.contract.Search
import com.triarc.tutorial.android.room_rxjava.ipc.EventBus
import com.triarc.tutorial.android.room_rxjava.entity.Book as BookEntity

/**
 * Created by Devanshu Verma on 24/1/19
 */
class SearchPresenter(private val model: Book.Model): AbstractBasePresenter<Search.View>(), Search.Presenter {

    private var eventBus: EventBus? = null

    override fun attachView(view: Search.View) {
        super.attachView(view)
        view.onInitialiseLayout()
        view.onInitialiseAdapter()
        view.onInitialiseListener()
        view.onRequestKeyBoard()
    }

    override fun postEvent(event: String, book: BookEntity) {
        val bundle = Bundle()
        bundle.putSerializable(IntentKey.SEARCH_RESULT, book)
        eventBus?.send(event, bundle)
    }

    override fun hideKeyBoard() {
        getView()?.onHideKeyBoard()
    }

    override fun registerEventBus() {
        getContext()?.let {context ->
            eventBus = EventBus(context)
        }
    }

    override fun unRegisterEventBus() {
        eventBus?.detach()
    }

    override fun updateSearchScreen(constraint: String) {
        getView()?.onFilterUpdated(constraint, 0)
        getView()?.onBooksByMatchingTitle(null)
    }

    override fun getBookByMatchingTitle(constraint: String) {
        subscription.add(model.getBookByMatchingTitle(constraint).subscribe({books ->
            getView()?.onFilterUpdated(constraint, books?.size ?: 0)
            getView()?.onBooksByMatchingTitle(books)
        }, {
            logger.error("Could't retrieve books ${it.cause}")
        }))

    }
}
