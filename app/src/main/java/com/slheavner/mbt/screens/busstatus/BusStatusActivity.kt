package com.slheavner.mbt.screens.busstatus

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
        val drawerHeader = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(ColorDrawable(MDColor.YELLOW_700))
                .addProfiles(
                        ProfileDrawerItem().withIcon(R.mipmap.ic_launcher)
                ).build()
        setSupportActionBar(toolbar)
        drawer = DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(busDrawerItem)
                .withAccountHeader(drawerHeader)
                .withOnDrawerItemClickListener { view, position, drawerItem ->
                    if (position == 1) {
                        showBusStatusFragment()
                        drawer?.closeDrawer()
                    }
                    true
                }
                .build()
        toolbar.setBackgroundColor(MDColor.BLUE_100)
        toolbar.setTitleTextColor(MDColor.WHITE)
        showBusStatusFragment()
    }

    fun showBusStatusFragment() {
        val busStatusFragment = BusStatusFragment.newInstance()
        supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.root_fragment_holder, busStatusFragment)
                ?.commit()
    }
}
