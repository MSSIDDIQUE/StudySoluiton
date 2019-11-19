package com.baymax.studysoluiton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val config = AppBarConfiguration(
            setOf(
                R.id.destination_home,
                R.id.destination_study_material,
                R.id.destination_time_table,
                R.id.destination_toppers
        ),
        drawer_layout
        )
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment)

        setUpActionBar(navController,config)
        setUpBottomNavMenu(navController)
        setUpDrawerMenu(navController)
    }

    private fun setUpBottomNavMenu(navController: NavController)
    {
        bottom_nav?.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }

    private fun setUpDrawerMenu(navController: NavController)
    {
        nav_view?.let {
            NavigationUI.setupWithNavController(it,navController)
        }
    }

    private fun setUpActionBar(navController: NavController,config: AppBarConfiguration)
    {
        NavigationUI.setupActionBarWithNavController(this,navController,config)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController  = Navigation.findNavController(this,R.id.nav_host_fragment)
        val navigated = NavigationUI.onNavDestinationSelected(item!!,navController)
        val current = navController.currentDestination?.id
        this.supportActionBar?.setTitle(current.toString())
        return navigated||super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val config = AppBarConfiguration(
            setOf(
                R.id.destination_home,
                R.id.destination_study_material,
                R.id.destination_time_table,
                R.id.destination_toppers
            ),
            drawer_layout
        )
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment),config)
    }
}