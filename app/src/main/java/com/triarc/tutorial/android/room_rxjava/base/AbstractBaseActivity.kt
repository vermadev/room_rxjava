package com.triarc.tutorial.android.room_rxjava.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import com.triarc.tutorial.android.room_rxjava.intf.ActivityBuilder
import com.triarc.tutorial.android.room_rxjava.intf.Logger
import com.triarc.tutorial.android.room_rxjava.logger.LoggerImpl

/**
 * Created by Devanshu Verma on 15/1/19
 */
abstract class AbstractBaseActivity: AppCompatActivity(), BaseView, ActivityBuilder {

    protected val logger: Logger = LoggerImpl.getLogger(this)

    override fun onBackPressed() {
        val fragmentCount = supportFragmentManager.backStackEntryCount
        if (fragmentCount > 1) {
            supportFragmentManager.popBackStack()
        } else if (fragmentCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun getContext(): Context? = this

    override fun getActivity(): FragmentActivity? = this

    override fun launch(intent: Intent) {
        startActivity(intent)
    }

    protected fun loadFragment(fragmentContainer: Int, fragmentClassName: String, bundle: Bundle? = null, transition: Int = FragmentTransaction.TRANSIT_NONE, transitionStyle: Int = FragmentTransaction.TRANSIT_NONE) {
        val fragment       = findFragmentById(fragmentContainer)
        val targetFragment = Fragment.instantiate(this, fragmentClassName)
        if (null != bundle)
            targetFragment.arguments = bundle

        manageFragment(fragmentContainer, targetFragment, fragmentClassName,
            if (fragment != null) fragment::class.java.name else fragmentClassName, transition, transitionStyle)
    }

    private fun findFragmentById(fragmentContainer: Int): Fragment? {
        return supportFragmentManager.findFragmentById(fragmentContainer)
    }

    private fun findFragmentByTag(currentFragment: String?): Fragment? {
        return supportFragmentManager.findFragmentByTag(currentFragment)
    }

    private fun manageFragment(fragmentContainer: Int, fragment: Fragment, tag: String?, currentFragment: String?, transition: Int, transitionStyle: Int) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        if (findFragmentByTag(currentFragment) != null) {
            findFragmentByTag(currentFragment)?.let {
                transaction.hide(it)
            }
        }

        transaction.add(fragmentContainer, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.setTransition(transition)
        transaction.setTransitionStyle(transitionStyle)
        transaction.commitAllowingStateLoss()
        manager.executePendingTransactions()
    }
}
