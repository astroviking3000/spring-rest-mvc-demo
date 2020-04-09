package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.mapper.CustomerMapper;
import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.domain.Customer;
import com.astroviking.springrestmvcdemo.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {

  public static final long ID = 1L;
  public static final String FIRST_NAME = "Tyler";
  public static final String LAST_NAME = "Gray";

  CustomerService customerService;

  @Mock CustomerRepository customerRepository;

  @BeforeEach
  void setUp() {
    customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
  }

  @Test
  void getAllCustomers() {
    // given
    List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
    when(customerRepository.findAll()).thenReturn(customers);

    // when
    List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

    // then
    assertEquals(3, customerDTOS.size());
  }

  @Test
  void getById() {
    // given
    Customer customer = new Customer(ID, FIRST_NAME, LAST_NAME);
    when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
    // when
    CustomerDTO customerDTO = customerService.getById(1L);

    // then
    assertEquals(customer.getId(), customerDTO.getId());
  }
}
