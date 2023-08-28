package com.hoangtien2k3.food_order_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hoangtien2k3.food_order_app.model.FoodDomain
import com.hoangtien2k3.food_order_app.helper.ManagementCart
import com.hoangtien2k3.food_order_app.`interface`.ChangeNumberItemsListener
import com.hoangtien2k3.food_order_app.R

class CartListAdapter() : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    private lateinit var foodDomainList: ArrayList<FoodDomain>
    private lateinit var managementCart: ManagementCart
    private lateinit var changeNumberItemsListener: ChangeNumberItemsListener

    constructor(
        foodDomainList: ArrayList<FoodDomain>,
        managementCart: ManagementCart,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) : this() {
        this.foodDomainList = foodDomainList
        this.managementCart = managementCart
        this.changeNumberItemsListener = changeNumberItemsListener
    }

    constructor(
        foodDomainList: ArrayList<FoodDomain>,
        context: Context,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) : this() {
        this.foodDomainList = foodDomainList
        this.managementCart = ManagementCart(context)
        this.changeNumberItemsListener = changeNumberItemsListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.titleTxt)
        val feedEachItem: TextView = itemView.findViewById(R.id.feeEachItem)
        val pic: ImageView = itemView.findViewById(R.id.picCart)
        val totalEachItem: TextView = itemView.findViewById(R.id.totalEachItem)
        val num: TextView = itemView.findViewById(R.id.numberItemTxt)
        val plusItem: ImageView = itemView.findViewById(R.id.plusCartBtn)
        val minusItem: ImageView = itemView.findViewById(R.id.minusCartBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.viewholder_cart, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return foodDomainList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFood = foodDomainList[position]

        holder.title.text = currentFood.title
        holder.feedEachItem.text = currentFood.fee.toString()
        val totalAmount = (Math.round((currentFood.numberInCart * currentFood.fee) * 100) / 100).toString()
        holder.totalEachItem.text = totalAmount
        holder.num.text = currentFood.numberInCart.toString()

        val drawableResourceId = holder
            .itemView
            .context
            .resources
            .getIdentifier(currentFood.pic, "drawable", holder.itemView.context.packageName)

        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.pic)

        // tăng số lượng sản phẩm, nếu sản phẩm về 0 thì đồng thời remove
        holder.plusItem.setOnClickListener {
            managementCart.plusNumberFood(foodDomainList, position, object : ChangeNumberItemsListener {
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.changed()
                }
            })
        }

        // desc number product in Cart, if product equals zero: remove
        holder.minusItem.setOnClickListener {
            managementCart.minusNumberFood(foodDomainList, position, object : ChangeNumberItemsListener {
                override fun changed() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.changed()
                }
            })
        }
    }
}

