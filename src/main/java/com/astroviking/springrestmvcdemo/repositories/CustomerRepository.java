package com.astroviking.springrestmvcdemo.repositories;

import com.astroviking.springrestmvcdemo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}
