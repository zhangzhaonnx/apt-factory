package com.zhangzhao.app;

import com.zhangzhao.annotation.Factory;

@Factory(
    id = "Calzone",
    type = Meal.class
)
public class CalzonePizza implements Meal {

  @Override
  public float getPrice() {
    return 8.5f;
  }
}
