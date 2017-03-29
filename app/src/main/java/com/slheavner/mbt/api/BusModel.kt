package com.slheavner.mbt.api

import com.google.gson.annotations.SerializedName

/**
 * Created by samuelheavner on 3/28/17.
 */
data class BusModel(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("number") val number: String,
        @SerializedName("service") val service: String,
        @SerializedName("firstrun") val firstrun: String,
        @SerializedName("lastrun") val lastrun: String,
        @SerializedName("runtime") val runtime: String,
        @SerializedName("polylineColor") val polylineColor: String,
        @SerializedName("updateInfo") val updateInfo: Long,
        @SerializedName("updateLocations") val updateLocations: Long,
        @SerializedName("updateLine") val updateLine: Long,
        @SerializedName("locations") val locations: List<BusLocation>,
        @SerializedName("polyline") val polyline: List<BusPoint>

){
    fun BusModel(){

    }

    data class BusLocation(
            @SerializedName("desc") val desc: String,
            @SerializedName("lat") val lat: Double,
            @SerializedName("lon") val lon: Double,
            @SerializedName("long") val time: Long,
            @SerializedName("bus") val bus: Int
    )

    data class BusPoint(
            @SerializedName("lat") val lat: Double,
            @SerializedName("lon") val lon: Double
    )

}