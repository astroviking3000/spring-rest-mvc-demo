package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.model.CategoryDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.CategoryListDTO;

public interface CategoryService {

  CategoryListDTO getAllCategories();

  CategoryDTO getCategoryByName(String name);
}
