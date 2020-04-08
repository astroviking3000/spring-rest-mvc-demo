package com.astroviking.springrestmvcdemo.api.v1.mapper;

import com.astroviking.springrestmvcdemo.api.v1.model.CategoryDTO;
import com.astroviking.springrestmvcdemo.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CategoryMapper {

  CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

  CategoryDTO categoryToCategoryDTO(Category category);
}
