package com.astroviking.springrestmvcdemo.repositories;

import com.astroviking.springrestmvcdemo.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {}
