package com.astroviking.springrestmvcdemo.controllers;

import com.astroviking.springrestmvcdemo.api.v1.model.CategoryDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.CategoryListDTO;
import com.astroviking.springrestmvcdemo.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

  public static final String BASE_URL = "/api/v1/categories";

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public CategoryListDTO getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("{name}")
  @ResponseStatus(HttpStatus.OK)
  public CategoryDTO getCategoryByName(@PathVariable String name) {
    return categoryService.getCategoryByName(name);
  }
}
