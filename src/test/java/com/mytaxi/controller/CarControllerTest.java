package com.mytaxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.TestData;
import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.CarServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarControllerTest extends TestData {

    private MockMvc mockMvc;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarController carController;

    @Mock
    private CarServiceImpl carService;

    private JacksonTester<CarDTO> carDTOJacksonTester;
    private static final ObjectMapper mapper = new ObjectMapper();

    private CarDO carDO;
    private CarDTO carDTO;

    @Before
    public void testSetup() {
        carDO = TestData.createCarDO();
        carDTO = TestData.createCarDTO();
        carMapper = new CarMapper();
        JacksonTester.initFields(this, new ObjectMapper());
        MockitoAnnotations.initMocks(CarController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(carController).dispatchOptions(true).build();
    }

    @Test
    public void shouldCreateCar() throws Exception {
        given(carService.create(isA(CarDO.class))).willReturn(carDO);
        String carDataAsJsonString = mapper.writeValueAsString(carDTO);
        MockHttpServletResponse mvcResponse = mockMvc.perform(
                post("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content(carDataAsJsonString))
                .andDo(print())
                .andReturn().getResponse();
        assertThat(mvcResponse.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(mvcResponse.getContentAsString()).isEqualTo(
                carDTOJacksonTester.write(carDTO).getJson()
        );
        Assert.assertTrue(mvcResponse.getContentAsString().contains("WB-1234"));
    }

    @Test
    public void shouldThrowExceptionForInvalidCreateCarData() throws Exception {
        given(carService.create(isA(CarDO.class))).willThrow(ConstraintsViolationException.class);
        String carDataAsJsonString = mapper.writeValueAsString(carDTO);
        MockHttpServletResponse mvcResponse = mockMvc.perform(
                post("/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(carDataAsJsonString))
                .andDo(print())
                .andReturn().getResponse();
        assertThat(mvcResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(mvcResponse.getContentAsString()).isEmpty();
    }

    @Test
    public void shouldFindCarById() throws Exception {
        given(carService.findCarById(10L)).willReturn(carDO);
        Long carId = 10L;
        MockHttpServletResponse mvcResponse = mockMvc.perform(
                get("/v1/cars/" + carId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andReturn().getResponse();
        assertThat(mvcResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mvcResponse.getContentAsString()).isEqualTo(
                carDTOJacksonTester.write(carDTO).getJson()
        );
        verify(carService, times(1)).findCarById(10L);
    }

    @Test
    public void shouldThrowExceptionForInvalidFindCarData() throws Exception {
        Long carId = 10L;
        given(carService.findCarById(carId)).willThrow(EntityNotFoundException.class);
        MockHttpServletResponse mvcResponse = mockMvc.perform(
                get("/v1/cars/" + carId)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andReturn().getResponse();
        assertThat(mvcResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(mvcResponse.getContentAsString()).isEmpty();
        verify(carService, times(1)).findCarById(carId);
    }

    @Test
    public void shouldThrowExceptionForInvalidDeleteCarData() throws Exception {
        doThrow(EntityNotFoundException.class).when( carService).delete(isA(Long.class));
        MockHttpServletResponse mvcResponse = mockMvc.perform(
                delete("/v1/cars/" + 12345))
                .andDo(print())
                .andReturn().getResponse();
        assertThat(mvcResponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(mvcResponse.getContentAsString()).isEmpty();
        verify(carService, times(1)).delete(isA(Long.class));
        verifyNoMoreInteractions(carService);
    }
}