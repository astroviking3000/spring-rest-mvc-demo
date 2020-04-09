package com.astroviking.springrestmvcdemo.controllers;

import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.CustomerListDTO;
import com.astroviking.springrestmvcdemo.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<CustomerListDTO> getAllCustomers() {
    return new ResponseEntity<CustomerListDTO>(
        new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
    return new ResponseEntity<CustomerDTO>(customerService.getById(id), HttpStatus.OK);
  }
}
