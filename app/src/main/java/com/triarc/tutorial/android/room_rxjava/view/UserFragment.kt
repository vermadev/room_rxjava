package com.triarc.tutorial.android.room_rxjava.view

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.adapter.UserListAdapter
import com.triarc.tutorial.android.room_rxjava.application.RoomRxJavaTutorialApplication
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseFragment
import com.triarc.tutorial.android.room_rxjava.contract.User
import com.triarc.tutorial.android.room_rxjava.intf.ItemClickListener
import kotlinx.android.synthetic.main.fragment_user.*
import javax.inject.Inject
import com.triarc.tutorial.android.room_rxjava.entity.User as UserEntity

/**
 * Created by Devanshu Verma on 15/1/19
 */
class UserFragment: AbstractBaseFragment(), User.View, ItemClickListener {

    private var userListAdapter: UserListAdapter? = null

    private var recyclerLayoutManager: RecyclerView.LayoutManager? = null

    @Inject
    lateinit var presenter: User.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ((activity as? BaseFragmentActivity)?.application as? RoomRxJavaTutorialApplication)?.getApplicationComponent()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        rootView = inflater.inflate(R.layout.fragment_user, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.getExistingUsers()
    }

    override fun onUserSaved() {
        user_name.text?.clear()
        email.text?.clear()
        school.text?.clear()
        grade.text?.clear()
    }

    override fun onUserDeleted(user: UserEntity) {
        Toast.makeText(context, "User '${user.name}' Deleted successfully", Toast.LENGTH_SHORT).show()
    }

    override fun onUpdateView(users: List<UserEntity>?) {
        swipe_container.isRefreshing = false
        userListAdapter?.updateList(users)
    }

    override fun onInvalidInput(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onInitialiseView() {
        user_recycler.isNestedScrollingEnabled = false

        recyclerLayoutManager = LinearLayoutManager(context)
        user_recycler.layoutManager = recyclerLayoutManager
        user_recycler.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
   }

    override fun onInitialiseAdapter() {
        userListAdapter = UserListAdapter()
        userListAdapter?.setItemClickListener(this)

        user_recycler.adapter = userListAdapter
    }

    override fun onInitialiseListener() {
        swipe_container.setOnRefreshListener {
            presenter.getExistingUsers()
        }

        save.setOnClickListener {
            presenter.saveUser( user_name.text?.toString(), email.text?.toString(), school.text?.toString(), grade.text?.toString())
        }
    }

    override fun onItemClick(view: View?, tag: Any?) {
        if(tag != null) {
            presenter.deleteUser(userListAdapter?.getItem(tag as Int) as? UserEntity)
        }
    }
}
