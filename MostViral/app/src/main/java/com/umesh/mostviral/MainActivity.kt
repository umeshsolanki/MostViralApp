package com.umesh.mostviral

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.umesh.mostviral.modals.Meme
import com.umesh.mostviral.network.GalleryApi
import com.umesh.mostviral.network.RetrofitApi
import com.umesh.mostviral.network.Test
import com.umesh.mostviral.ui.home.MemeAdapter
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity(),MemeAdapter.Companion.MemeClickListener {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        Log.d(TAG,"Initiating Request")


    }

    //Meme click is delegated to activity which can to used to share this action/data
    //across fragments
    override fun memeClicked(meme: Meme) {
        Log.d(TAG,"Meme: ${meme.title} is clicked")
        AlertDialog.Builder(this).setTitle("Clicked Meme")
            .setMessage("${meme.title}").create().show()
    }
}
