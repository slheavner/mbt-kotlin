package com.slheavner.mbt.screens.busstatus

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.slheavner.mbt.R
import com.slheavner.mbt.api.BusModel

/**
 * Created by samuelheavner on 3/23/17.
 */
class BusStatusAdapter : RecyclerView.Adapter<BusStatusAdapter.ViewHolder>(){

    var busData: List<BusModel>? = null

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.statusText?.text = busData?.get(position)?.locations?.get(0)?.desc
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_bus_status, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return busData?.size ?: 0
    }

    fun setData(buses: List<BusModel>){
        this.busData = buses
        this.notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statusText: TextView by bindView<TextView>(R.id.status_text_one)
    }

}