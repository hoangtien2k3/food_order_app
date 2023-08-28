package com.hoangtien2k3.food_order_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoangtien2k3.food_order_app.model.CategoryDomain
import com.hoangtien2k3.food_order_app.R

class CategoryAdapter(private val categoryDomainList: List<CategoryDomain>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
        val categoryPic: ImageView = itemView.findViewById(R.id.categoryPic)
        val mainLayout: ConstraintLayout = itemView.findViewById(R.id.mainLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.viewholder_category, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categoryDomainList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categoryDomainList[position]
        holder.categoryName.text = category.title

        val picResId = when (position) {
            0 -> Pair(R.drawable.bg_cat_background1, "cat_1")
            1 -> Pair(R.drawable.bg_cat_background2, "cat_2")
            2 -> Pair(R.drawable.bg_cat_background3, "cat_3")
            3 -> Pair(R.drawable.bg_cat_background4, "cat_4")
            4 -> Pair(R.drawable.bg_cat_background5, "cat_5")
            else -> Pair(0, "")
        }

        holder.mainLayout.setBackgroundResource(picResId.first)

        if (picResId.second.isNotEmpty()) {
            val drawableResourceId = holder
                .itemView
                .context
                .resources
                .getIdentifier(picResId.second, "drawable", holder.itemView.context.packageName)

            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(holder.categoryPic)
        }
    }
}
