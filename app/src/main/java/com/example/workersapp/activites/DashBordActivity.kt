package com.example.workersapp.activites


import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.workersapp.R
import com.example.workersapp.databinding.ActivityDashBordBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashBordActivity : CardActivity() {

    private lateinit var binding: ActivityDashBordBinding

    private var mylong = ""
    private var mylit = ""
    override fun onCreate(savedInstanceState: Bundle?) {


        val intent = intent
        val extras = intent.extras
        val litetude = extras!!.getString("latitudeText")
        val longetude = extras!!.getString("longitudeText")

        if (longetude != null) {
            mylong=longetude
        }
        if (litetude != null) {
            mylit=litetude
        }


//        val bundle = Bundle()
//        bundle.putString("latitudeText", "37.423423")
//        bundle.putString("longitudeText", "-122.083953")
//        val fragobj = DashboardFragment()
//        fragobj.arguments = bundle

        super.onCreate(savedInstanceState)

        if (getSupportActionBar() != null) {
            getSupportActionBar()?.hide()
        }


        binding = ActivityDashBordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_dash_bord)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


    }

    fun getMyLonData(): String? {
        return mylong
    }
    fun getMyLitData(): String? {
        return mylit
    }

    override fun onBackPressed() {
        doubleBackToExit()
    }
}