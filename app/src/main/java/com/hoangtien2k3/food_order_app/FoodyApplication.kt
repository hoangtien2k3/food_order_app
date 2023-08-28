package com.hoangtien2k3.food_order_app

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp

class FoodyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d("App","onCreate application")
        // using FirebaseApp initalization
        FirebaseApp.initializeApp(applicationContext)
    }

}