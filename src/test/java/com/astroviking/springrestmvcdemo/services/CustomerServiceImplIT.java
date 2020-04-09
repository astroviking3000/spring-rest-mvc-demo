package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.mapper.CustomerMapper;
import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.bootstrap.Bootstrap;
import com.astroviking.springrestmvcdemo.domain.Customer;
import com.astroviking.springrestmvcdemo.repositories.CategoryRepository;
import com.astroviking.springrestmvcdemo.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT {

  public static final String NAME = "Test";
  @Autowired CategoryRepository categoryRepository;

  @Autowired CustomerRepository customerRepository;

  CustomerService customerService;

  @BeforeEach
  void setUp() {
    Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
    bootstrap.run();

    customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
  }

  @Test
  public void patchCustomerUpdateFirstName() {
    Long customerId = getCustomerIdValue();
    CustomerDTO originalCustomer = customerService.getById(customerId);
    CustomerDTO submittedCustomer = new CustomerDTO();
    submittedCustomer.setFirstName(NAME);

    CustomerDTO patchedCustomer = customerService.patchCustomer(customerId, submittedCustomer);

    assertEquals(originalCustomer.getId(), patchedCustomer.getId());
    assertEquals(NAME, patchedCustomer.getFirstName());
    assertEquals(originalCustomer.getLastName(), patchedCustomer.getLastName());
  }

  @Test
  public void patchCustomerUpdateLastName() {
    Long customerId = getCustomerIdValue();
    CustomerDTO originalCustomer = customerService.getById(customerId);
    CustomerDTO submittedCustomer = new CustomerDTO();
    submittedCustomer.setLastName(NAME);

    CustomerDTO patchedCustomer = customerService.patchCustomer(customerId, submittedCustomer);

    assertEquals(originalCustomer.getId(), patchedCustomer.getId());
    assertEquals(originalCustomer.getFirstName(), patchedCustomer.getFirstName());
    assertEquals(NAME, patchedCustomer.getLastName());
  }

  private Long getCustomerIdValue() {
    List<Customer> customerList = customerRepository.findAll();

    return customerList.get(0).getId();
  }
}
