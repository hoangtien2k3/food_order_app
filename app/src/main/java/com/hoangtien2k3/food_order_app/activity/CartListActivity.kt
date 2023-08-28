package com.hoangtien2k3.food_order_app.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hoangtien2k3.food_order_app.adapter.CartListAdapter
import com.hoangtien2k3.food_order_app.helper.ManagementCart
import com.hoangtien2k3.food_order_app.`interface`.ChangeNumberItemsListener
import com.hoangtien2k3.food_order_app.R
import com.hoangtien2k3.food_order_app.databinding.ActivityShowDetailBinding

class CartListActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var recyclerViewList: RecyclerView
    private lateinit var managementCart: ManagementCart
    lateinit var totalFeeTxt: TextView
    lateinit var taxTxt: TextView
    lateinit var deliveryTxt: TextView
    lateinit var totalTxt: TextView
    lateinit var emptyTxt: TextView
    private var tax: Double = 0.0
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)

        managementCart = ManagementCart(this)

        initView()
        initList()
        calculateCart()
        bottomNavigation()
    }


    private fun bottomNavigation() {
        val cartBtn: LinearLayout = findViewById(R.id.cartBtn)
        val homeBtn: LinearLayout = findViewById(R.id.homeBtn)

        cartBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        homeBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun initView() {
        recyclerViewList = findViewById(R.id.cartView)
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        taxTxt = findViewById(R.id.taxTxt)
        totalTxt = findViewById(R.id.totalTxt)
        emptyTxt = findViewById(R.id.emptyTxt)
        scrollView = findViewById(R.id.scrollView)
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewList.layoutManager = linearLayoutManager

        val adapter = CartListAdapter(managementCart.getListCart(), this, object : ChangeNumberItemsListener {
            override fun changed() {
                calculateCart()
            }
        })

        // nếu trong Cart không có sản phẩm thì Text "Không Có Sản Phẩm Nào" sẽ được show lên layout
        recyclerViewList.adapter = adapter
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.visibility = View.VISIBLE
            scrollView.visibility = View.GONE
        } else {
            emptyTxt.visibility = View.GONE
            scrollView.visibility = View.VISIBLE
        }
    }


    private fun calculateCart() {
        val percentTax: Double = 0.02
        val delivery: Double = 10.0

        val totalFee = managementCart.getTotalFee()
        val tax = (totalFee * percentTax).roundTo2DecimalPlaces()
        val total = (totalFee + tax + delivery).roundTo2DecimalPlaces()
        val itemTotal = totalFee.roundTo2DecimalPlaces()

        totalFeeTxt.text = itemTotal.toString()
        taxTxt.text = tax.toString()
        deliveryTxt.text = delivery.toString()
        totalTxt.text = total.toString()
    }

    fun Double.roundTo2DecimalPlaces(): Double {
        return (Math.round(this * 100) / 100.0)
    }


}