package com.triarc.tutorial.android.room_rxjava.presenter

import android.view.MenuItem
import com.triarc.tutorial.android.room_rxjava.base.AbstractBasePresenter
import com.triarc.tutorial.android.room_rxjava.contract.IssueBook
import com.triarc.tutorial.android.room_rxjava.entity.Book
import com.triarc.tutorial.android.room_rxjava.entity.Register
import com.triarc.tutorial.android.room_rxjava.ipc.EventBus

/**
 * Created by Devanshu Verma on 30/1/19
 */
class IssueBookPresenter(private val model: IssueBook.Model): AbstractBasePresenter<IssueBook.View>(), IssueBook.Presenter {

    private var eventBus: EventBus? = null

    override fun attachView(view: IssueBook.View) {
        super.attachView(view)
        view.onInitialiseView()
        view.onInitialiseListener()
    }

    override fun selectBook(book: Book) {
        subscription.add(model.getUsers().subscribe({users ->
            getView()?.onBookSelected(book, users)
        }, {
            logger.error("Couldn't retrieve users from table ${it.cause}")
        }))
    }

    override fun registerEventBus(listener: EventBus.Listener, vararg events: String) {
        getContext()?.let {context ->
            eventBus = EventBus(context, listener)
            events.forEach {event ->
                eventBus?.attach(event)
            }
        }
    }

    override fun unRegisterEventBus() {
        eventBus?.detach()
    }

    override fun optionsMenuSelected(item: MenuItem?) {
        getView()?.onOptionsMenuSelected(item?.itemId ?: 0)
    }

    override fun addEntriesToRegister(entries: List<Register>) {
        if(entries.isNotEmpty()) {
            subscription.add(model.addEntriesToRegister(entries).subscribe({
                subscription.add(model.getEntriesFromRegister().subscribe({entries ->
                    logger.info("Found ${entries?.size} entries in register")
                    entries?.forEach {
                        logger.info("${it.name} has issued ${it.title}")
                    }
                }, {
                    logger.error("Couldn't retrieve entries from register ${it.cause}")
                }))
            }, {
                logger.error("Couldn't save entries in register ${it.cause}")
            }))
        }
    }
}
