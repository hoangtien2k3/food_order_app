package com.hoangtien2k3.food_order_app.activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hoangtien2k3.food_order_app.data.DataInitHome
import com.hoangtien2k3.food_order_app.R
import com.hoangtien2k3.food_order_app.adapter.CategoryAdapter
import com.hoangtien2k3.food_order_app.adapter.PopularAdapter
import com.hoangtien2k3.food_order_app.model.FoodDomain

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CategoryAdapter
    private lateinit var adapter2: PopularAdapter
    private lateinit var recyclerViewCategoryList: RecyclerView
    private lateinit var recyclerViewPopularList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewCategory()
        recyclerViewPopular()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        val cartBtn: LinearLayout = findViewById(R.id.cartBtn)
        val homeBtn: LinearLayout = findViewById(R.id.homeBtn)

        cartBtn.setOnClickListener {
            startActivity(Intent(this, CartListActivity::class.java))
        }

        homeBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun recyclerViewCategory() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategoryList = findViewById(R.id.recyclerView1)
        recyclerViewCategoryList.layoutManager = linearLayoutManager

/*
        val category: ArrayList<CategoryDomain> = ArrayList()
        category.add(CategoryDomain("Pizza", "cat_1"))
        category.add(CategoryDomain("Bánh", "cat_2"))
        category.add(CategoryDomain("Trái", "cat_3"))
        category.add(CategoryDomain("Qủa", "cat_4"))
        category.add(CategoryDomain("Mỳ", "cat_5"))
*/

        adapter = CategoryAdapter(DataInitHome.listDemoCategoryDomain)
        recyclerViewCategoryList.adapter = adapter

    }

    private fun recyclerViewPopular() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularList = findViewById(R.id.recyclerView2)
        recyclerViewPopularList.layoutManager = linearLayoutManager

/*
        val foodList: ArrayList<FoodDomain>  = ArrayList<FoodDomain>()
        foodList.add(FoodDomain("Bánh Pizza", "pizza1", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 975.0));
        foodList.add(FoodDomain("Ngũ cốc", "pizza1", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 15.0));
        foodList.add(FoodDomain("Nước uống", "cat_2", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 46.0));
        foodList.add(FoodDomain("Trái cây", "pop_1", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 27.0));
        foodList.add(FoodDomain("CoCa-Cola", "pop_2", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 75.0));
*/

        adapter2 = PopularAdapter(DataInitHome.listDemoFoodDomain, onClickItem = ::startDetailPopular)
        recyclerViewPopularList.adapter = adapter2

    }

    private fun startDetailPopular(item : FoodDomain) {
        val intent = Intent(this, ShowDetailActivity::class.java).apply {
            putExtra(POPULAR_ITEM_KEY, item)
        }
        startActivity(intent)
    }

    companion object {
        private const val POPULAR_ITEM_KEY = "object1"
    }

}