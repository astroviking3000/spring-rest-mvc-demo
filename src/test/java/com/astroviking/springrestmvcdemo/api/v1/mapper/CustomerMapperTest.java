package com.astroviking.springrestmvcdemo.api.v1.mapper;

import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

  public static final String FIRST_NAME = "Tyler";
  public static final String LAST_NAME = "Gray";
  public static final long ID = 1L;
  CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  @Test
  void customerToCustomerDTO() {
    // given
    Customer customer = new Customer(ID, FIRST_NAME, LAST_NAME);

    // when
    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

    // then
    assertEquals(ID, customerDTO.getId());
    assertEquals(FIRST_NAME, customerDTO.getFirstName());
    assertEquals(LAST_NAME, customerDTO.getLastName());
  }
}
