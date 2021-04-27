package com.example.currentcy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.currentcy.currency.Currencies
import com.example.currentcy.currency.CurrencyFragmentArgs
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        // get the parcelable object into a variable
        //val transferedCurrencyList = intent?.getStringExtra("transferableList")

        // find the navigation controller, set the new navigation graph and send the object to the new fragments
        //if (transferedCurrencyList != null) {
            //findNavController(R.id.main_nav_host_fragment).setGraph(R.navigation.main_navigation, CurrencyFragmentArgs(transferedCurrencyList).toBundle())
        //}

        setUpNavigation()
    }

    fun setUpNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_menu)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.navController)
    }
}