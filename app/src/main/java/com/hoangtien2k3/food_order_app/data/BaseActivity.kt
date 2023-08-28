package com.hoangtien2k3.food_order_app.data

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        loadData()
    }

    abstract fun initData()
    abstract fun loadData()
}