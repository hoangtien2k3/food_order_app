package com.hoangtien2k3.food_order_app.helper

import android.content.Context
import android.widget.Toast
import com.hoangtien2k3.food_order_app.model.FoodDomain
import com.hoangtien2k3.food_order_app.`interface`.ChangeNumberItemsListener

class ManagementCart {
    private lateinit var context: Context
    private lateinit var tinyDB: TinyDB

    constructor(context: Context) {
        this.context = context
        this.tinyDB = TinyDB(context)
    }

    public fun insertFood(item: FoodDomain): Unit {
        var listFood: ArrayList<FoodDomain> = getListCart()
        var existAlready: Boolean = false

        var n = 0
        for(i in 0 until listFood.size) {
            if (listFood[i].title.equals(item.title)) {
                existAlready = true;
                n = i
                break
            }
        }

        if (existAlready) {
            listFood[n].numberInCart = item.numberInCart
        } else {
            listFood.add(item)
        }

        tinyDB.putListObject("CartList", listFood)

        // in thông báo lên màn hình
        Toast.makeText(context, "Đã thêm vào giỏ hàng.", Toast.LENGTH_LONG).show()
    }

    public fun getListCart(): ArrayList<FoodDomain> {
        return tinyDB.getListObject("CartList")
    }


    // tăng giá tiền khi ấn nút
    public fun plusNumberFood(listFood: ArrayList<FoodDomain>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {
        listFood[position].numberInCart += 1
        tinyDB.putListObject("CartList", listFood)
        changeNumberItemsListener.changed()
    }

    // giảm giá tiền khi ấn nút
    public fun minusNumberFood(listFood: ArrayList<FoodDomain>, position: Int, changeNumberItemsListener: ChangeNumberItemsListener) {
        // cách 1:
       /* listFood[position].run {
            if (numberInCart == 1) {
                listFood.removeAt(position)
            } else {
                numberInCart -= 1
            }
        }*/


        // cách 2:
        if (listFood[position].numberInCart == 1) {
            listFood.removeAt(position)
        } else {
            listFood[position].numberInCart -= 1
        }

        tinyDB.putListObject("CartList", listFood)
        changeNumberItemsListener.changed()
    }


    // lấy giá tổng giá tiền
    public fun getTotalFee(): Double {
        val listFood: ArrayList<FoodDomain> = getListCart()

        var fee: Double = 0.0
        for(i in 0 until listFood.size) {
            fee += (listFood[i].fee * listFood[i].numberInCart)
        }

        return fee
    }


}