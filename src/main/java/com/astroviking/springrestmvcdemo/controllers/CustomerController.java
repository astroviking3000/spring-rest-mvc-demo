package com.astroviking.springrestmvcdemo.controllers;

import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.CustomerListDTO;
import com.astroviking.springrestmvcdemo.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

  public static final String BASE_URL = "/api/v1/customers";
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<CustomerListDTO> getAllCustomers() {
    return new ResponseEntity<>(
        new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
    return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
    CustomerDTO savedDto = customerService.create(customerDTO);
    return new ResponseEntity<>(savedDto, HttpStatus.OK);
  }

  @PutMapping("{id}")
  public ResponseEntity<CustomerDTO> update(
      @PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    CustomerDTO savedDto = customerService.update(id, customerDTO);
    return new ResponseEntity<>(savedDto, HttpStatus.OK);
  }

  @PatchMapping("{id}")
  public ResponseEntity<CustomerDTO> patch(
      @PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    CustomerDTO savedDto = customerService.patchCustomer(id, customerDTO);
    return new ResponseEntity<>(savedDto, HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    customerService.deleteCustomer(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
