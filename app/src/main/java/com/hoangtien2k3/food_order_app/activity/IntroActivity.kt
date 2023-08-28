package com.hoangtien2k3.food_order_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.hoangtien2k3.food_order_app.R

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        findViewById<ConstraintLayout>(R.id.startBtn).setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }

    }
}


