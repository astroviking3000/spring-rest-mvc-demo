package com.astroviking.springrestmvcdemo.api.v1.mapper;

import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  CustomerDTO customerToCustomerDTO(Customer customer);

  Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
