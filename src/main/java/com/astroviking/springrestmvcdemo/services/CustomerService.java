package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

  List<CustomerDTO> getAllCustomers();

  CustomerDTO getById(Long id);

  CustomerDTO create(CustomerDTO customerDTO);

  CustomerDTO update(Long id, CustomerDTO customerDTO);

  CustomerDTO patch(Long id, CustomerDTO customerDTO);
}
