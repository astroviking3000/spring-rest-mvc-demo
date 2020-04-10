package com.astroviking.springrestmvcdemo.controllers;

import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.CustomerListDTO;
import com.astroviking.springrestmvcdemo.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

  public static final String BASE_URL = "/api/v1/customers";
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public CustomerListDTO getAllCustomers() {
    return new CustomerListDTO(customerService.getAllCustomers());
  }

  @GetMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public CustomerDTO getById(@PathVariable Long id) {
    return customerService.getById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public CustomerDTO create(@RequestBody CustomerDTO customerDTO) {
    return customerService.create(customerDTO);
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public CustomerDTO update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    return customerService.update(id, customerDTO);
  }

  @PatchMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public CustomerDTO patch(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    return customerService.patchCustomer(id, customerDTO);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.OK)
  public void delete(@PathVariable Long id) {
    customerService.deleteCustomer(id);
  }
}
