package com.hoangtien2k3.food_order_app.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoangtien2k3.food_order_app.activity.ShowDetailActivity
import com.hoangtien2k3.food_order_app.model.FoodDomain
import com.hoangtien2k3.food_order_app.R

class PopularAdapter(private val popularFoodDomainList: List<FoodDomain>, private val onClickItem : (foodDomain : FoodDomain)-> Unit) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val fee: TextView = itemView.findViewById(R.id.fee)
        val pic: ImageView = itemView.findViewById(R.id.pic)
        val addBtn: TextView = itemView.findViewById(R.id.addBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_popular, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return popularFoodDomainList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemCurrent = popularFoodDomainList[position]
        holder.title.text = itemCurrent.title
        holder.fee.text = String.format("%.3f", itemCurrent.fee)

        val drawableResourceId = holder
            .itemView
            .context
            .resources
            .getIdentifier(itemCurrent.pic, "drawable", holder.itemView.context.packageName)

        // Glide: thư viện quản lý việc đẩy ảnh ...
        Glide.with(holder.itemView.context)
            .load(drawableResourceId)  // Đường dẫn URL của hình ảnh
            .into(holder.pic)          // ImageView để hiển thị hình ảnh

        holder.addBtn.setOnClickListener {
            onClickItem.invoke(itemCurrent)
        }

    }
}
