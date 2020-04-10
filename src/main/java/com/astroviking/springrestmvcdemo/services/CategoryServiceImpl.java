package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.mapper.CategoryMapper;
import com.astroviking.springrestmvcdemo.api.v1.model.CategoryDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.CategoryListDTO;
import com.astroviking.springrestmvcdemo.domain.Category;
import com.astroviking.springrestmvcdemo.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryMapper categoryMapper;
  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
    this.categoryMapper = categoryMapper;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public CategoryListDTO getAllCategories() {
    List<CategoryDTO> categoryDTOS =
        categoryRepository.findAll().stream()
            .map(categoryMapper::categoryToCategoryDTO)
            .collect(Collectors.toList());
    return new CategoryListDTO(categoryDTOS);
  }

  @Override
  public CategoryDTO getCategoryByName(String name) {
    Category category = categoryRepository.findByName(name);
    if (category == null) throw new ResourceNotFoundException();
    return categoryMapper.categoryToCategoryDTO(category);
  }
}
