package com.smart.school.school

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import com.smart.school.Main.activity.BaseActivity
import com.smart.school.Main.activity.Intro
import com.smart.school.R
import com.smart.school.app.config.Config
import com.smart.school.app.config.iConfig
import com.smart.school.util.BackPressCloseHandler
import com.smart.school.util.helper.AppInfoHelper
import com.smart.school.util.helper.ToastHelper
import kotlinx.android.synthetic.main.activity_school_main.*
import kotlinx.android.synthetic.main.app_bar_schoolmain.*
import kotlinx.android.synthetic.main.main_school_main.*

class SchoolMain : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    var progressBar: ProgressBar? = null
    var strPoi_no: String? = null
    var user_no: String? = null
    var backPressCloseHandler: BackPressCloseHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_school_main)
        user_no = Config.getInstance().getLoginInfo(this).user_no
        bt_bus_route.setOnClickListener(this)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.top_menu_s)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.s4, R.string.s8)
        val version = AppInfoHelper.getAppVersionCode(this)
        val ver1 = AppInfoHelper.getAppVersion(this)
        nav_view.menu.findItem(R.id.item_version).title = "Ver $version ($ver1)"
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        backPressCloseHandler = BackPressCloseHandler(this)
    }

    override fun onResume() {
        super.onResume()
        //    getShop();
    }

    override fun onBackPressed() {
        if (strPoi_no == "0") {
            backPressCloseHandler!!.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_hotnews -> {
            }
            R.id.item_school_suggest -> {
            }
            R.id.item_test_schedule -> {
            }
            R.id.item_food_review -> {
            }
            R.id.item_reserve_question -> {
            }
            R.id.item_restaurant_cctv -> {
                intent = Intent(this@SchoolMain, RestaurantCctv::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.item_call_teacher -> {
            }
            R.id.item_car_time -> {
                intent = Intent(this@SchoolMain, BusRoutine::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.item_teacher_time -> {
                intent = Intent(this@SchoolMain, TeacherTime::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
            R.id.item_logout -> {
                Config.getInstance().setLoginCheck(this@SchoolMain, false)
                ToastHelper.showToast(this@SchoolMain, getString(R.string.s2937))
                intent = Intent(this@SchoolMain, Intro::class.java)
                intent.putExtra(iConfig.KEY_TYPE, iConfig.TAG_LOGIN)
                intent.putExtra(iConfig.KEY_GO_ACT, iConfig.TAG_MAIN)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
            }
        }
        return true
    }

    override fun onClick(v: View?) {
        when(v){
            bt_bus_route -> {
                val intent: Intent
                intent = Intent(this@SchoolMain, BusRoutine::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

        }
    }

}