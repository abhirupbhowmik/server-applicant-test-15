package com.mytaxi.controller.mapper;

import com.mytaxi.TestData;
import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.domainobject.CarDO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class CarDriverMapperTest {

    @Mock
    private CarDriverMapper carDriverMapper;

    @Mock
    private DriverMapper driverMapper;

    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(CarDriverMapper.class);
    }

    @Test
    public void carDOShouldMapCarDriverDTO(){

        CarDO carDO = TestData.createCarDO();
        CarDriverDTO carDriverDTO = carDriverMapper.makeCarWithDriverDTO(carDO);

        assertThat(carDriverDTO).isNotNull();
        assertThat(carDriverDTO.getCarDetails().getId()).isEqualTo(carDO.getId());
        assertThat(carDriverDTO.getCarDetails().getCarType()).isEqualTo(carDO.getCarType());
        assertThat(carDriverDTO.getCarDetails().getTotalSeat()).isEqualTo(carDO.getTotalSeat());
        assertThat(carDriverDTO.getCarDetails().getLicenseNo()).isEqualTo(carDO.getLicenseNo());
        assertThat(carDriverDTO.getCarDetails().getManufacturer()).isEqualTo(carDO.getManufacturer());
        assertThat(carDriverDTO.getCarDetails().getRating()).isEqualTo(carDO.getRating());
        assertThat(carDriverDTO.getDriverDetails().getId()).isEqualTo(carDO.getDriver().getId());
    }



}