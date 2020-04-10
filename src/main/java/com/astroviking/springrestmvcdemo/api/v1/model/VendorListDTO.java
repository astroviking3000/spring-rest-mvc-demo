package com.astroviking.springrestmvcdemo.api.v1.model;

import lombok.Data;

import java.util.List;

@Data
public class VendorListDTO {
  private MetaDTO meta;
  private List<VendorDTO> vendors;

  public VendorListDTO(List<VendorDTO> vendorDTOs) {
    this.meta = new MetaDTO(vendorDTOs.size());
    this.vendors = vendorDTOs;
  }
}
