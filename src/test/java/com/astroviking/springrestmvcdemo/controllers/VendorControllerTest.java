package com.astroviking.springrestmvcdemo.controllers;

import com.astroviking.springrestmvcdemo.api.v1.model.VendorDTO;
import com.astroviking.springrestmvcdemo.api.v1.model.VendorListDTO;
import com.astroviking.springrestmvcdemo.services.VendorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.astroviking.springrestmvcdemo.controllers.VendorController.BASE_URL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {VendorController.class})
class VendorControllerTest extends AbstractControllerTest {

  public static final String NAME = "Some vendor";
  public static final long ID = 1L;

  @MockBean VendorService vendorService;

  @Autowired MockMvc mockMvc;

  @Test
  void getAllVendors() throws Exception {
    List<VendorDTO> vendorDTOS = Arrays.asList(new VendorDTO(), new VendorDTO());
    VendorListDTO vendorListDTO = new VendorListDTO(vendorDTOS);
    given(vendorService.getAllVendors()).willReturn(vendorListDTO);

    mockMvc
        .perform(get(BASE_URL))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.meta.count", equalTo(2)))
        .andExpect(jsonPath("$.vendors", hasSize(2)));
  }

  @Test
  void createVendor() throws Exception {
    VendorDTO unsavedDTO = new VendorDTO(null, NAME);
    VendorDTO savedDTO = new VendorDTO(ID, NAME);
    given(vendorService.create(unsavedDTO)).willReturn(savedDTO);

    mockMvc
        .perform(
            post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(unsavedDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)));
  }

  @Test
  void deleteVendor() throws Exception {
    mockMvc.perform(delete(BASE_URL + "/" + ID)).andExpect(status().isOk());
    verify(vendorService, times(1)).delete(ID);
  }

  @Test
  void getVendorById() throws Exception {
    VendorDTO vendorDTO = new VendorDTO(ID, NAME);
    given(vendorService.getById(ID)).willReturn(vendorDTO);

    mockMvc
        .perform(get(BASE_URL + "/" + ID))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.name", equalTo(NAME)));
  }

  @Test
  void patchVendor() throws Exception {
    VendorDTO vendorDTO = new VendorDTO(null, NAME);
    VendorDTO patchedDTO = new VendorDTO(ID, NAME);
    given(vendorService.patchVendor(ID, vendorDTO)).willReturn(patchedDTO);

    mockMvc
        .perform(
            patch(BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.name", equalTo(NAME)));
  }

  @Test
  void updateVendor() throws Exception {
    VendorDTO vendorDTO = new VendorDTO(null, NAME);
    VendorDTO patchedDTO = new VendorDTO(ID, NAME);
    given(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).willReturn(patchedDTO);

    mockMvc
        .perform(
            put(BASE_URL + "/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(vendorDTO)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", equalTo(1)))
        .andExpect(jsonPath("$.name", equalTo(NAME)));
  }
}
