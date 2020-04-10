package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.mapper.VendorMapper;
import com.astroviking.springrestmvcdemo.api.v1.model.VendorDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.VendorListDTO;
import com.astroviking.springrestmvcdemo.domain.Vendor;
import com.astroviking.springrestmvcdemo.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class VendorServiceImplTest {

  public static final String NAME = "Some Vendor";
  public static final long ID = 1L;
  @Mock VendorRepository vendorRepository;

  VendorService vendorService;

  @BeforeEach
  void setUp() {
    vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
  }

  @Test
  void getAllVendors() {
    // given
    List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor());
    when(vendorRepository.findAll()).thenReturn(vendors);

    // when
    VendorListDTO vendorListDTO = vendorService.getAllVendors();

    // then
    int size = vendorListDTO.getVendors().size();
    assertEquals(2, size);
    assertEquals(2, vendorListDTO.getMeta().getCount());
  }

  @Test
  void create() {
    // given
    VendorDTO unsavedDTO = new VendorDTO(null, NAME);
    Vendor savedVendor = new Vendor(ID, NAME);
    when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

    // when
    VendorDTO savedDto = vendorService.create(unsavedDTO);

    // then
    assertEquals(ID, savedDto.getId());
    assertEquals(NAME, savedDto.getName());
  }

  @Test
  void delete() {
    vendorService.delete(ID);

    verify(vendorRepository, times(1)).deleteById(ID);
  }

  @Test
  void getById() {
    Vendor vendor = new Vendor(ID, NAME);
    when(vendorRepository.findById(ID)).thenReturn(Optional.of(vendor));

    VendorDTO vendorDTO = vendorService.getById(ID);

    assertEquals(ID, vendorDTO.getId());
    assertEquals(NAME, vendorDTO.getName());
  }

  @Test
  void patchVendor() {
    Vendor vendor = new Vendor(ID, NAME);
    when(vendorRepository.findById(ID)).thenReturn(Optional.of(vendor));
    VendorDTO editedDTO = new VendorDTO(null, NAME);
    when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);

    VendorDTO vendorDTO = vendorService.patchVendor(ID, editedDTO);

    assertEquals(vendor.getId(), vendorDTO.getId());
    assertEquals(NAME, vendorDTO.getName());
  }

  @Test
  void updateVendor() {
    Vendor vendor = new Vendor(ID, NAME);
    when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor);
    VendorDTO usavedDTO = new VendorDTO(null, NAME);

    VendorDTO vendorDTO = vendorService.updateVendor(ID, usavedDTO);

    assertEquals(vendor.getId(), vendorDTO.getId());
    assertEquals(vendor.getName(), vendorDTO.getName());
  }
}
