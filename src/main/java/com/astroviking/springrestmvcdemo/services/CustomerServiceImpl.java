package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.mapper.CustomerMapper;
import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

  CustomerRepository customerRepository;
  CustomerMapper customerMapper;

  public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
    this.customerRepository = customerRepository;
    this.customerMapper = customerMapper;
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll().stream()
        .map(customerMapper::customerToCustomerDTO)
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getById(Long id) {
    return customerRepository
        .findById(id)
        .map(customerMapper::customerToCustomerDTO)
        .orElseThrow(RuntimeException::new);
  }
}
