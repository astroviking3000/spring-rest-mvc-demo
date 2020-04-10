package com.astroviking.springrestmvcdemo.controllers;

import com.astroviking.springrestmvcdemo.api.v1.model.CategoryDTO;
import com.astroviking.springrestmvcdemo.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class CategoryControllerTest {

  public static final String NAME = "Jim";

  @Mock CategoryService categoryService;

  @InjectMocks CategoryController categoryController;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
  }

  @Test
  public void testListCategories() throws Exception {
    CategoryDTO category1 = new CategoryDTO();
    category1.setId(1L);
    category1.setName(NAME);

    CategoryDTO category2 = new CategoryDTO();
    category1.setId(2L);
    category1.setName("Bob");

    List<CategoryDTO> categoryDTOList = Arrays.asList(category1, category2);

    when(categoryService.getAllCategories()).thenReturn(categoryDTOList);

    mockMvc
        .perform(get(CategoryController.BASE_URL).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.categories", hasSize(2)));
  }

  @Test
  public void testGetByNameCategories() throws Exception {
    CategoryDTO category1 = new CategoryDTO();
    category1.setId(1L);
    category1.setName(NAME);

    when(categoryService.getCategoryByName(anyString())).thenReturn(category1);

    mockMvc
        .perform(
            get(CategoryController.BASE_URL + "/" + NAME).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name", equalTo(NAME)));
  }
}
