package com.triarc.tutorial.android.room_rxjava.view

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.triarc.tutorial.android.room_rxjava.R
import com.triarc.tutorial.android.room_rxjava.adapter.SearchResultListAdapter
import com.triarc.tutorial.android.room_rxjava.application.RoomRxJavaTutorialApplication
import com.triarc.tutorial.android.room_rxjava.base.AbstractBaseFragment
import com.triarc.tutorial.android.room_rxjava.common.Event
import com.triarc.tutorial.android.room_rxjava.contract.Search
import com.triarc.tutorial.android.room_rxjava.entity.Book
import com.triarc.tutorial.android.room_rxjava.intf.ItemClickListener
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

/**
 * Created by Devanshu Verma on 23/1/19
 */
class SearchFragment: AbstractBaseFragment(), Search.View, ItemClickListener {

    private var recyclerLayoutManager: RecyclerView.LayoutManager? = null

    private var searchResultAdapter: SearchResultListAdapter? = null

    private var keyword: String? = null

    @Inject
    lateinit var presenter: Search.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ((activity as? BaseFragmentActivity)?.application as? RoomRxJavaTutorialApplication)?.getApplicationComponent()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        rootView = inflater.inflate(R.layout.fragment_search, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.attachView(this)
    }

    override fun onStart() {
        super.onStart()

        presenter.registerEventBus()
    }

    override fun onStop() {
        super.onStop()

        presenter.unRegisterEventBus()
    }

    override fun onHideKeyBoard() {
        val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    override fun onFilterUpdated(constraint: String, length: Int) {
        if ((TextUtils.isEmpty(keyword) && TextUtils.isEmpty(constraint)) || TextUtils.equals(constraint, keyword))
            return

        keyword = constraint
        updateNoSearchResult(constraint, length)

        updateSearchTips(constraint)
    }

    override fun onRequestKeyBoard() {
        search_box.requestFocus()
        val inputManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    override fun onInitialiseLayout() {
        recyclerLayoutManager = LinearLayoutManager(context)
        search_recycler.layoutManager = recyclerLayoutManager
        search_recycler.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onInitialiseAdapter() {
        searchResultAdapter = SearchResultListAdapter(context)
        searchResultAdapter?.setItemClickListener(this)

        search_recycler.adapter = searchResultAdapter
    }

    override fun onInitialiseListener() {
        search_box.addTextChangedListener(textChangedListener)
    }

    override fun onBooksByMatchingTitle(books: List<Book>?) {
        searchResultAdapter?.keyword = keyword ?: ""
        searchResultAdapter?.updateList(books)
    }

    override fun onItemClick(view: View?, tag: Any?) {
        val book = searchResultAdapter?.getItem(tag as Int) as Book
        presenter.postEvent(Event.Click.SEARCH_RESULT, book)

        presenter.hideKeyBoard()

        activity?.onBackPressed()
    }

    private fun updateSearchTips(constraint: String) {
        val visibility = if (!TextUtils.isEmpty(constraint)) View.GONE else View.VISIBLE
        search_tips.visibility = visibility
    }

    private fun updateNoSearchResult(constraint: String, length: Int) {
        if (TextUtils.isEmpty(constraint)) {
            search_no_results.visibility = View.GONE
        } else {
            search_no_results.visibility = if (length > 0) View.GONE else View.VISIBLE
        }
    }

    private val textChangedListener = object : TextWatcher {
        override fun beforeTextChanged(constraint: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(constraint: CharSequence, start: Int, before: Int, count: Int) {
            if (null != context) {
                if (constraint.toString().isNotEmpty()) {
                    presenter.getBookByMatchingTitle(constraint.toString())
                } else {
                    presenter.updateSearchScreen(constraint.toString())
                }
            }
        }

        override fun afterTextChanged(s: Editable) {}
    }
}
