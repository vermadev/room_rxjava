package com.triarc.tutorial.android.room_rxjava.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.adapter.RegisterListAdapter
import com.triarc.tutorial.android.room_rxjava.application.RoomRxJavaTutorialApplication
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseActivity
import com.triarc.tutorial.android.room_rxjava.common.IntentKey
import com.triarc.tutorial.android.room_rxjava.contract.Register
import com.triarc.tutorial.android.room_rxjava.entity.Entries
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

/**
 * Created by Devanshu Verma on 15/1/19
 */
class HomeActivity: AbstractBaseActivity(), Register.View {

    private var registerListAdapter: RegisterListAdapter? = null

    private var recyclerLayoutManager: RecyclerView.LayoutManager? = null

    @Inject
    lateinit var presenter: Register.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as? RoomRxJavaTutorialApplication)?.getApplicationComponent()?.inject(this)

        setContentView(R.layout.activity_home)

        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.updateRegister()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        presenter.optionsMenuSelected(item)

        return super.onOptionsItemSelected(item)
    }

    override fun onUpdateView(register: List<Entries>?) {
        swipe_container.isRefreshing = false
        registerListAdapter?.updateList(register)
    }

    override fun onInitialiseView() {
        setSupportActionBar(toolbar as Toolbar)
        supportActionBar?.title = ""

        recyclerLayoutManager = LinearLayoutManager(applicationContext)
        register_recycler.layoutManager = recyclerLayoutManager
        register_recycler.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onInitialiseAdapter() {
        registerListAdapter = RegisterListAdapter()
        register_recycler.adapter = registerListAdapter
    }

    override fun onInitialiseListener() {
        swipe_container.setOnRefreshListener {
            presenter.updateRegister()
        }
    }

    override fun onOptionsMenuSelected(id: Int) {
        val intent = Intent(getContext(), BaseFragmentActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        when(id) {
            com.triarc.tutorial.android.room_rxjava.R.id.toolbar_add_user -> {
                intent.putExtra(IntentKey.FRAGMENT_NAME, UserFragment::class.java.name)
            }
            com.triarc.tutorial.android.room_rxjava.R.id.toolbar_add_book -> {
                intent.putExtra(IntentKey.FRAGMENT_NAME, BookFragment::class.java.name)
            }
            com.triarc.tutorial.android.room_rxjava.R.id.toolbar_issue_book -> {
                intent.putExtra(IntentKey.FRAGMENT_NAME, IssueBookFragment::class.java.name)
            }
        }
        launch(intent)
    }
}
