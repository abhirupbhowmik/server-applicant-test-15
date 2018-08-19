package com.mytaxi.controller.mapper;

import com.mytaxi.TestData;
import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.domainobject.CarDO;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class CarDriverMapperTest {

    private CarDriverMapper carDriverMapper;

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
        assertThat(carDriverDTO.getDriverDetails().getUsername()).isEqualTo(carDO.getDriver().getUsername());
        assertThat(carDriverDTO.getDriverDetails().getPassword()).isEqualTo(carDO.getDriver().getPassword());
        assertThat(carDriverDTO.getDriverDetails().getStatus()).isEqualTo(carDO.getDriver().getOnlineStatus());
    }
}