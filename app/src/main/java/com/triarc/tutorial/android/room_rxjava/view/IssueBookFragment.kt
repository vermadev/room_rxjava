package com.triarc.tutorial.android.room_rxjava.view

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.*
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.application.RoomRxJavaTutorialApplication
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseFragment
import com.triarc.tutorial.android.room_rxjava.common.Event
import com.triarc.tutorial.android.room_rxjava.common.IntentKey
import com.triarc.tutorial.android.room_rxjava.contract.IssueBook
import com.triarc.tutorial.android.room_rxjava.entity.Book
import com.triarc.tutorial.android.room_rxjava.entity.User
import com.triarc.tutorial.android.room_rxjava.intf.ActivityCallback
import com.triarc.tutorial.android.room_rxjava.ipc.EventBus
import kotlinx.android.synthetic.main.fragment_issue_book.*
import javax.inject.Inject
import android.widget.AdapterView.OnItemSelectedListener
import com.triarc.tutorial.android.room_rxjava.common.TimeUtil
import com.triarc.tutorial.android.room_rxjava.entity.Register

/**
 * Created by Devanshu Verma on 21/1/19
 */
class IssueBookFragment: AbstractBaseFragment(), IssueBook.View, EventBus.Listener {

    private var callback: ActivityCallback? = null

    private val events = listOf(Event.Click.SEARCH_RESULT)

    private val entryMap: HashMap<Int, Int> = hashMapOf()

    @Inject
    lateinit var presenter: IssueBook.Presenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = (context as? BaseFragmentActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ((activity as? BaseFragmentActivity)?.application as? RoomRxJavaTutorialApplication)?.getApplicationComponent()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        rootView = inflater.inflate(R.layout.fragment_issue_book, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()

        presenter.registerEventBus(this, *events.toTypedArray())
    }

    override fun onStop() {
        super.onStop()

        presenter.unRegisterEventBus()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_issue_book, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        presenter.optionsMenuSelected(item)
        return super.onOptionsItemSelected(item)
    }

    override fun onEvent(event: String, bundle: Bundle?) {
        if(TextUtils.equals(Event.Click.SEARCH_RESULT, event)) {
            bundle?.let {
                val book = it.getSerializable(IntentKey.SEARCH_RESULT) as Book
                presenter.selectBook(book)
            }
        }
    }

    override fun onBookSelected(book: Book, users: List<User>?) {
        if(entryMap.containsKey(book.id)) {
            Toast.makeText(context, "Book is already in issue list", Toast.LENGTH_SHORT).show()
            return
        }

        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.list_item_issue_book, layout_issue_book, false)

        view.findViewById<TextView>(R.id.book_name).text   = book.title
        view.findViewById<TextView>(R.id.author).text      = book.author
        view.findViewById<TextView>(R.id.publication).text = book.publication

        val list = arrayListOf<String>()

        users?.forEachIndexed { index, user ->
            list.add(index, user.name)
        }

        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list)

        view.findViewById<Spinner>(R.id.spinner).onItemSelectedListener = object : OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                entryMap[book.id] = users!![p2].id

                Toast.makeText(context, "Index: $p2 List :${entryMap.size}", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<Spinner>(R.id.spinner).adapter = adapter

        view.tag = book

        layout_issue_book.addView(view)
    }

    override fun onInitialiseView() {
        setHasOptionsMenu(true)
    }

    override fun onInitialiseListener() {
        issue.setOnClickListener {
            if(entryMap.isNotEmpty()) {
                val entries: ArrayList<Register> = arrayListOf()
                val keys = entryMap.keys
                keys.forEach {key ->
                    val entry = Register(key, entryMap[key]!!)
                    entry.dueDate   = System.currentTimeMillis() + TimeUtil.getDaysInMilliSec(TimeUtil.Constant.DAY_5)
                    entry.issueDate = System.currentTimeMillis()

                    entries.add(entry)
                }
                presenter.addEntriesToRegister(entries)
                activity?.onBackPressed()
            }
        }
    }

    override fun onOptionsMenuSelected(id: Int) {
        callback?.listener(SearchFragment::class.java.name)
    }
}
