package com.slheavner.mbt.screens.busstatus

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.bindView
import com.slheavner.mbt.R
import com.slheavner.mbt.activity.BaseFragment
import com.slheavner.mbt.api.BusApi
import com.slheavner.mbt.api.BusModel

/**
 * Created by samuelheavner on 3/28/17.
 */
open class BusStatusFragment : SwipeRefreshLayout.OnRefreshListener, BaseFragment() {

    val recyclerView by bindView<RecyclerView>(R.id.recycler_view)
    val swipeRefreshLayout by bindView<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
    val busStatusAdapter = BusStatusAdapter()
    val busApi = BusApi()

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_bus_status, null)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        busStatusAdapter.setHasStableIds(true)
        recyclerView.layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = busStatusAdapter
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.isRefreshing = true
        onRefresh()
    }

    override fun onRefresh() {
        busApi.getBusList().doOnTerminate {
            swipeRefreshLayout.isRefreshing = false
        }.subscribe(this::updateBusList, this::onRefreshFailed)
    }

    fun updateBusList(bus: BusModel) {
        busStatusAdapter.addItem(bus)
    }

    fun onRefreshFailed(error: Throwable) {
        error.printStackTrace()
    }


    companion object {
        val TAG = "BusStatusFragment"
        fun newInstance(): BusStatusFragment {
            val fragment = BusStatusFragment()
            return fragment
        }

        fun tag(): String {
            return TAG
        }
    }

}