package com.zhangzhao.app;

import com.zhangzhao.annotation.Factory;

@Factory(
    id = "Margherita",
    type = Meal.class
)
public class MargheritaPizza implements Meal {
  @Override public float getPrice() {
    return 6.0f;
  }
}
