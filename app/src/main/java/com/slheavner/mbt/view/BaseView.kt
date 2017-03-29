package com.slheavner.mbt.view

import com.slheavner.mbt.activity.BaseActivity

/**
 * Created by samuelheavner on 3/23/17.
 */

open class BaseView(activity: BaseActivity) {
    var activity: BaseActivity? = null
    init{
        this.activity = activity;
    }
}
