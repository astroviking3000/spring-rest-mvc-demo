package com.astroviking.springrestmvcdemo.api.v1.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Customer Model")
public class CustomerDTO {
  @ApiModelProperty(hidden = true)
  private Long id;

  @ApiModelProperty(value = "First name", example = "John")
  private String firstName;

  @ApiModelProperty(value = "First name", example = "Smith")
  private String lastName;
}
