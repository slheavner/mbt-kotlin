package com.slheavner.mbt.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by samuelheavner on 3/28/17.
 */
class BusApi {

    val client = OkHttpClient()

    fun getBusList(): Observable<BusModel> {
        return Observable.defer {
            val request = Request.Builder().url("https://morgantownbustracker.org/initialize").build()
            val response = client.newCall(request).execute()
            val data = response.body().string()
            val buses = Gson().fromJson(data, arrayOf<BusModel>().javaClass)
            Observable.from(buses)
        }.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
}