package com.astroviking.springrestmvcdemo.api.v1.mapper;

import com.astroviking.springrestmvcdemo.api.v1.model.VendorDTO;
import com.astroviking.springrestmvcdemo.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

  VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

  Vendor vendorDtoToVendor(VendorDTO vendorDTO);

  VendorDTO venderToVendorDTO(Vendor vendor);
}
