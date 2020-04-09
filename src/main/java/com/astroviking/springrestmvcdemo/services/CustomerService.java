package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

  List<CustomerDTO> getAllCustomers();

  CustomerDTO getById(Long id);

  CustomerDTO create(CustomerDTO customerDTO);

  CustomerDTO update(Long id, CustomerDTO customerDTO);

  CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

  void deleteCustomer(Long id);
}
