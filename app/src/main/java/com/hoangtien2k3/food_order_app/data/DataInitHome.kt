package com.hoangtien2k3.food_order_app.data

import com.hoangtien2k3.food_order_app.model.CategoryDomain
import com.hoangtien2k3.food_order_app.model.FoodDomain

object DataInitHome {
    // list Demo value FoodDomain
    val listDemoFoodDomain = listOf<FoodDomain>(
        FoodDomain("Bánh Pizza", "pizza1", "bánh pizza là loại bánh cực kỳ thơm ngon và hấp dẫn.", 195.0),
        FoodDomain("Ngũ cốc", "pizza1", "Ngũ cốc là loại thức uống cực kỳ thơm ngon và hấp dẫn.", 15.0),
        FoodDomain("Nước uống", "cat_2", "Nước uống là loại đồ uống cực kỳ thơm ngon và hấp dẫn.", 46.0),
        FoodDomain("Trái cây", "pop_1", "Trái cây là loại hoa quả cực kỳ thơm ngon và hấp dẫn.", 27.0),
        FoodDomain("CoCa-Cola", "pop_2", "CoCa-CoLa là loại đồ uống cực kỳ thơm ngon và hấp dẫn.", 75.0)
    )

    // list demo CategoryDomain
    val listDemoCategoryDomain = listOf<CategoryDomain>(
        CategoryDomain("Pizza", "cat_1"),
        CategoryDomain("Bánh", "cat_2"),
        CategoryDomain("Trái", "cat_3"),
        CategoryDomain("Qủa", "cat_4"),
        CategoryDomain("Mỳ", "cat_5"),
    )
}