package com.astroviking.springrestmvcdemo.api.v1.mapper;

import com.astroviking.springrestmvcdemo.api.v1.model.CategoryDTO;
import com.astroviking.springrestmvcdemo.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

  CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

  @Test
  void categoryToCategoryDTO() {

    // given
    Category category = new Category();
    category.setName("Joe");
    category.setId(1L);

    // when
    CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

    // then
    assertEquals(category.getId(), categoryDTO.getId());
    assertEquals(category.getName(), categoryDTO.getName());

  }
}
