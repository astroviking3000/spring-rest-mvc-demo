package com.astroviking.springrestmvcdemo.api.v1.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomerListDTO {
  private MetaDTO meta;
  private List<CustomerDTO> customers;

  public CustomerListDTO(List<CustomerDTO> customerDTOs) {
    this.customers = customerDTOs;
    this.meta = new MetaDTO(customerDTOs.size());
  }
}
