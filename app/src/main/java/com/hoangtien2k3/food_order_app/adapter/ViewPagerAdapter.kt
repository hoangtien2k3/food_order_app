package com.hoangtien2k3.food_order_app.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.hoangtien2k3.food_order_app.fragment.FavoriteFragment
import com.hoangtien2k3.food_order_app.fragment.HomeFragment
import com.hoangtien2k3.food_order_app.fragment.MyPageFragment

class ViewPagerAdapter : FragmentPagerAdapter {
    constructor(fm: FragmentManager, behavior: Int) : super(fm, behavior)

    override fun getCount(): Int {
        return 3; // số lượng tab
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> HomeFragment()
            1 -> FavoriteFragment()
            2 -> MyPageFragment()
            else -> HomeFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title: String = when(position) {
            0 -> "Home"
            1 -> "Favorite"
            2 -> "My Page"
            else -> "Unknown"
        }
        return title // setting title cho view
    }

}