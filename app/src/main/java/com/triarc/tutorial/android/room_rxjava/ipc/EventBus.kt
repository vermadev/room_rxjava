package com.triarc.tutorial.android.room_rxjava.ipc

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.triarc.tutorial.android.room_rxjava.controller.BroadcastController
import com.triarc.tutorial.android.room_rxjava.intf.Logger
import com.triarc.tutorial.android.room_rxjava.logger.LoggerImpl
import java.lang.ref.WeakReference
import java.util.HashMap

/**
 * Created by Devanshu Verma on 23/1/19
 */
class EventBus(context: Context, private val eventListener: Listener? = null) {

    private val logger: Logger = LoggerImpl.getLogger(this)

    private val eventMap: HashMap<String, String> = HashMap()

    private var context: WeakReference<Context> = WeakReference(context)

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            intent.action?.let {event ->
                logger.debug("Received '$event' on Event Bus")
                if (eventMap.containsValue(event)) {
                    logger.debug("Delivered '$event' on Event Bus")
                    eventListener?.onEvent(event, intent.getBundleExtra(DATA_BUNDLE))
                }
            }
        }
    }

    fun send(event: String, bundle: Bundle? = null) {
        val intent = Intent(event)
        if (bundle != null)
            intent.putExtra(DATA_BUNDLE, bundle)

        BroadcastController.sendLocalBroadcast(context.get(), intent)
        logger.debug("Posting '$event' on Event Bus")
    }

    fun attach(event: String) {
        eventMap[event] = event
        BroadcastController.registerLocalReceiver(context.get(), event, receiver)
    }

    fun detach() {
        BroadcastController.unregisterLocalReceiver(context.get(), receiver)
    }

    interface Listener {
        fun onEvent(event: String, bundle: Bundle? = null)
    }

    private companion object {
        private const val DATA_BUNDLE = "DataBundle"
    }
}
