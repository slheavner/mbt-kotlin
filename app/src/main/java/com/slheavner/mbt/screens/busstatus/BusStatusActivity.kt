package com.slheavner.mbt.screens.busstatus

import android.app.Application
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.bindView
import com.github.bassaer.library.MDColor
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.slheavner.mbt.R
import com.slheavner.mbt.activity.BaseActivity

class BusStatusActivity : BaseActivity() {

    internal val toolbar: Toolbar by bindView(R.id.toolbar)
    internal val busDrawerItem: PrimaryDrawerItem = PrimaryDrawerItem().withName("Bus Statuses")
    internal var drawer: Drawer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        setupToolbar()
        setupDrawer()
        showBusStatusFragment()
    }

    internal fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setBackgroundColor(MDColor.INDIGO_900)
        toolbar.setTitleTextColor(MDColor.WHITE)
    }

    internal fun setupDrawer() {
        val drawerHeader = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(ColorDrawable(MDColor.YELLOW_700))
                .addProfiles(
                        ProfileDrawerItem().withIcon(R.mipmap.ic_launcher)
                ).build()
        drawer = DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(busDrawerItem)
                .withAccountHeader(drawerHeader)
                .withOnDrawerItemClickListener { view, position, drawerItem ->
                    when (position) {
                        1 -> showBusStatusFragment()
                    }
                    drawer?.closeDrawer()
                    true
                }
                .build()
    }

    fun showBusStatusFragment() {
        val busStatusFragment =
                supportFragmentManager.findFragmentByTag(BusStatusFragment.tag())
                        ?: BusStatusFragment.newInstance()
        supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.root_fragment_holder, busStatusFragment, BusStatusFragment.tag())
                ?.commit()
    }
}
