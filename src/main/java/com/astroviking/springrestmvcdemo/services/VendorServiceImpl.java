package com.astroviking.springrestmvcdemo.services;

import com.astroviking.springrestmvcdemo.api.v1.mapper.VendorMapper;
import com.astroviking.springrestmvcdemo.api.v1.model.VendorDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.VendorListDTO;
import com.astroviking.springrestmvcdemo.domain.Vendor;
import com.astroviking.springrestmvcdemo.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

  private final VendorRepository vendorRepository;
  private final VendorMapper vendorMapper;

  public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
    this.vendorRepository = vendorRepository;
    this.vendorMapper = vendorMapper;
  }

  @Override
  public VendorListDTO getAllVendors() {
    List<VendorDTO> vendorDTOList =
        vendorRepository.findAll().stream()
            .map(vendorMapper::venderToVendorDTO)
            .collect(Collectors.toList());
    return new VendorListDTO(vendorDTOList);
  }

  @Override
  public VendorDTO create(VendorDTO vendorDTO) {
    Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
    return vendorMapper.venderToVendorDTO(vendorRepository.save(vendor));
  }

  @Override
  public void delete(Long id) {
    vendorRepository.deleteById(id);
  }

  @Override
  public VendorDTO getById(Long id) {
    return vendorRepository
        .findById(id)
        .map(vendorMapper::venderToVendorDTO)
        .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
    Vendor vendor = vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    if (vendorDTO.getName() != null && vendorDTO.getName().equals(vendor.getName())) {
      vendor.setName(vendorDTO.getName());
    }
    return vendorMapper.venderToVendorDTO(vendorRepository.save(vendor));
  }

  @Override
  public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
    Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
    vendor.setId(id);
    return vendorMapper.venderToVendorDTO(vendorRepository.save(vendor));
  }
}
