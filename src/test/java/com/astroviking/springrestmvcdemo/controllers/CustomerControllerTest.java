package com.astroviking.springrestmvcdemo.controllers;

import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.services.CustomerService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

  public static final long ID = 1L;
  public static final String FIRST_NAME = "Jim";
  @Mock CustomerService customerService;

  @InjectMocks CustomerController customerController;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
  }

  @Test
  void getAllCustomers() throws Exception {
    CustomerDTO customer1 = new CustomerDTO(1L, "Jim", "Blue");
    CustomerDTO customer2 = new CustomerDTO(2L, "John", "Red");
    List<CustomerDTO> customerList = Arrays.asList(customer1, customer2);

    when(customerService.getAllCustomers()).thenReturn(customerList);

    mockMvc
        .perform(get("/api/v1/customers").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.customers", hasSize(2)));
  }

  @Test
  void getById() throws Exception {
    CustomerDTO customer1 = new CustomerDTO(ID, FIRST_NAME, "Blue");

    when(customerService.getById(anyLong())).thenReturn(customer1);

    mockMvc
        .perform(get("/api/v1/customers/" + ID).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
  }
}
