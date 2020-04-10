package com.astroviking.springrestmvcdemo.api.v1.mapper;

import com.astroviking.springrestmvcdemo.api.v1.model.VendorDTO;
import com.astroviking.springrestmvcdemo.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendorMapperTest {

  public static final String NAME = "Some Vendor";
  public static final long ID = 1L;

  VendorMapper vendorMapper = VendorMapper.INSTANCE;

  @Test
  public void testVendorToDTO() {
    Vendor vendor = new Vendor(ID, NAME);

    VendorDTO vendorDTO = vendorMapper.venderToVendorDTO(vendor);

    Long id = vendorDTO.getId();
    assertEquals(ID, id);
    assertEquals(NAME, vendorDTO.getName());
  }

  @Test
  public void testDTOToVendor() {
    VendorDTO vendorDTO = new VendorDTO(ID, NAME);

    Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);

    Long id = vendor.getId();
    assertEquals(ID, id);
    assertEquals(NAME, vendor.getName());
  }
}
