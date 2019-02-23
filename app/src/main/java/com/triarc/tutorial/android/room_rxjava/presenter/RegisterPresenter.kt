package com.triarc.tutorial.android.room_rxjava.presenter

import android.view.MenuItem
import com.triarc.tutorial.android.room_rxjava.base.AbstractBasePresenter
import com.triarc.tutorial.android.room_rxjava.contract.Register

/**
 * Created by Devanshu Verma on 15/1/19
 */
class RegisterPresenter(private val model: Register.Model): AbstractBasePresenter<Register.View>(), Register.Presenter {

    override fun attachView(view: Register.View) {
        super.attachView(view)
        view.onInitialiseView()
        view.onInitialiseAdapter()
        view.onInitialiseListener()
    }

    override fun updateRegister() {
        subscription.add(model.getEntriesFromRegister().subscribe({entries ->
            getView()?.onUpdateView(entries)
        }, {
            logger.error("Could't retrieve register entries ${it.cause}")
        }))
    }

    override fun optionsMenuSelected(item: MenuItem?) {
        if(item != null)
            getView()?.onOptionsMenuSelected(item.itemId)
    }
}
