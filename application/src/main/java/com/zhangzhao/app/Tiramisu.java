package com.zhangzhao.app;

import com.zhangzhao.annotation.Factory;

@Factory(
    id = "Tiramisu",
    type = Meal.class
)
public class Tiramisu implements Meal {

  @Override
  public float getPrice() {
    return 4.5f;
  }
}
