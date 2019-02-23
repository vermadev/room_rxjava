package com.triarc.tutorial.android.room_rxjava.base

import com.triarc.tutorial.android.room_rxjava.intf.Logger
import com.triarc.tutorial.android.room_rxjava.logger.LoggerImpl
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by Devanshu Verma on 13/1/19
 */
abstract class AbstractBasePresenter<View: BaseView>: BasePresenter<View> {

    protected val logger: Logger = LoggerImpl.getLogger(this)

    private var reference: WeakReference<View>? = null

    protected val subscription: CompositeDisposable = CompositeDisposable()

    override fun getView(): View? {
        return reference?.get()
    }

    override fun detachView() {
        reference?.clear()
        subscription.clear()
    }

    override fun attachView(view: View) {
        reference = WeakReference(view)
    }

    protected fun getContext() = getView()?.getContext()

    protected fun getActivity() = getView()?.getActivity()
}
