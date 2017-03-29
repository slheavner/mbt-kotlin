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

    var busData: MutableList<BusModel> = mutableListOf()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val bus = busData[position]
        val location = bus.locations[0]
        holder?.statusText?.text = location.desc
        holder?.routeNumberText?.text = bus.number
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_bus_status, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return busData.size
    }

    override fun getItemId(position: Int): Long {
        return busData[position].id.hashCode().toLong()
    }

    fun addItem(bus: BusModel){
        val index = busData.indexOfFirst {
            it.id == bus.id
        }
        if(index < 0){
            busData.add(bus)
            notifyItemInserted(busData.lastIndex)
        }else{
            busData[index] = bus
            notifyItemChanged(index)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statusText: TextView by bindView<TextView>(R.id.item_status_text)
        val routeNumberText: TextView by bindView<TextView>(R.id.item_route_number)
    }

}