package com.hoangtien2k3.food_order_app.model

import java.io.Serializable

class FoodDomain(var title: String, var pic: String, var description: String, var fee: Double, var numberInCart: Int) : Serializable{
    constructor(title: String, pic: String, description: String, fee: Double) : this(title, pic, description, fee, 0)
}