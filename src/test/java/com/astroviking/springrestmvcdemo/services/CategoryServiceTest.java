package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.mapper.CategoryMapper;
import com.astroviking.springrestmvcdemo.api.v1.model.CategoryDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.CategoryListDTO;
import com.astroviking.springrestmvcdemo.domain.Category;
import com.astroviking.springrestmvcdemo.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

  public static final Long ID = 2L;
  public static final String NAME = "Jimmy";

  CategoryService categoryService;

  @Mock CategoryRepository categoryRepository;

  @BeforeEach
  void setUp() {
    categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
  }

  @Test
  void getAllCategories() {
    // given
    List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
    when(categoryRepository.findAll()).thenReturn(categories);

    // when
    CategoryListDTO categoryListDTO = categoryService.getAllCategories();

    // then
    Integer size = categoryListDTO.getCategories().size();
    assertEquals(3, size);
  }

  @Test
  void getCategoryByName() {
    // given
    Category category = new Category();
    category.setId(ID);
    category.setName(NAME);

    when(categoryRepository.findByName(anyString())).thenReturn(category);

    // when
    CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

    // then
    assertEquals(ID, categoryDTO.getId());
    assertEquals(NAME, categoryDTO.getName());
  }
}
