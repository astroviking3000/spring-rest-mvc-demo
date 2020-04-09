package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.mapper.CustomerMapper;
import com.astroviking.springrestmvcdemo.api.v1.model.CustomerDTO;
import com.astroviking.springrestmvcdemo.domain.Customer;
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

  @Override
  public CustomerDTO create(CustomerDTO customerDTO) {
    Customer customer = customerMapper.customerDtoToCustomer(customerDTO);

    return saveCustomer(customer);
  }

  private CustomerDTO saveCustomer(Customer customer) {
    Customer savedCustomer = customerRepository.save(customer);

    CustomerDTO returnDTO = customerMapper.customerToCustomerDTO(savedCustomer);

    return returnDTO;
  }

  @Override
  public CustomerDTO update(Long id, CustomerDTO customerDTO) {
    Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
    customer.setId(id);

    return saveCustomer(customer);
  }

  @Override
  public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
    Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);

    if (customerDTO.getFirstName() != null) customer.setFirstName(customerDTO.getFirstName());

    if (customerDTO.getLastName() != null) customer.setLastName(customerDTO.getLastName());

    return saveCustomer(customer);
  }

  @Override
  public void deleteCustomer(Long id) {
    customerRepository.deleteById(id);
  }
}
