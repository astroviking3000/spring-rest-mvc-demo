package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.model.VendorDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.VendorListDTO;

public interface VendorService {

  VendorListDTO getAllVendors();

  VendorDTO create(VendorDTO vendorDTO);

  void delete(Long id);

  VendorDTO getById(Long id);

  VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

  VendorDTO updateVendor(Long id, VendorDTO vendorDTO);
}
