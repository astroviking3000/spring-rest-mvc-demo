package com.astroviking.springrestmvcdemo.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Vendor")
public class VendorDTO {
  @ApiModelProperty("Vendor ID")
  private Long id;

  @ApiModelProperty("Vendor name")
  private String name;
}
