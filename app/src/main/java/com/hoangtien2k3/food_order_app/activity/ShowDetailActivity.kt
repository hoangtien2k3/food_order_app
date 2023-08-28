package com.hoangtien2k3.food_order_app.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hoangtien2k3.food_order_app.model.FoodDomain
import com.hoangtien2k3.food_order_app.helper.ManagementCart
import com.hoangtien2k3.food_order_app.R
import com.hoangtien2k3.food_order_app.databinding.ActivityShowDetailBinding

class ShowDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowDetailBinding

    private lateinit var object1: FoodDomain
    private var numberOrder = 1
    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)
        getBundle()
    }

    private fun getBundle() {
        object1 = intent.getSerializableExtra("object1") as FoodDomain

        val drawableResourceId: Int =
            resources.getIdentifier(object1.pic, "drawable", packageName)

        Glide.with(this)
            .load(drawableResourceId)
            .into(binding.picFood)

        binding.titleTxt.text = object1.title
        binding.priceTxt.text = String.format("%.3f", object1.fee).replace(",", ".")
        binding.descriptionTxt.text = object1.description
        binding.numberOrderTxt.text = numberOrder.toString()

        binding.plusBtn.setOnClickListener {
            numberOrder += 1
            binding.numberOrderTxt.text = numberOrder.toString()
        }

        binding.minusBtn.setOnClickListener {
            if (numberOrder > 1) {
                numberOrder -= 1
                binding.numberOrderTxt.text = numberOrder.toString()
            }
        }

        binding.addToCartBtn.setOnClickListener {
            object1.numberInCart = numberOrder
            managementCart.insertFood(object1)
        }
    }

}
