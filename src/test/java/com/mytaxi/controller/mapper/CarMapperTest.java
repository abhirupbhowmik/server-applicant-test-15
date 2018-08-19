package com.mytaxi.controller.mapper;

import com.mytaxi.TestData;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class CarMapperTest {

    private CarMapper carMapper;

    @Test
    public void carDOShouldMapCarDTO(){

        CarDO carDO = TestData.createCarDO();
        CarDTO carDTO = carMapper.makeCarDTO(carDO);

        assertThat(carDTO).isNotNull();
        assertThat(carDTO.getId()).isEqualTo(carDO.getId());
        assertThat(carDTO.getLicenseNo()).isEqualTo(carDO.getLicenseNo());
        assertThat(carDTO.getTotalSeat()).isEqualTo(carDO.getTotalSeat());
        assertThat(carDTO.getRating()).isEqualTo(carDO.getRating());
        assertThat(carDTO.getCarType()).isEqualTo(carDO.getCarType());
        assertThat(carDTO.getManufacturer()).isEqualTo(carDO.getManufacturer());
    }


    @Test
    public void carDTOShouldMapCarDO(){

        CarDTO carDTO = TestData.createCarDTO();
        CarDO carDO = carMapper.makeCarDO(carDTO);

        assertThat(carDO).isNotNull();
        assertThat(carDO.getId()).isEqualTo(carDTO.getId());
        assertThat(carDO.getLicenseNo()).isEqualTo(carDTO.getLicenseNo());
        assertThat(carDO.getTotalSeat()).isEqualTo(carDTO.getTotalSeat());
        assertThat(carDO.getRating()).isEqualTo(carDTO.getRating());
        assertThat(carDO.getCarType()).isEqualTo(carDTO.getCarType());
        assertThat(carDO.getManufacturer()).isEqualTo(carDTO.getManufacturer());
    }

    @Test
    public void carDTOListShouldMapCarDOList(){

        List<CarDO> carDOList = TestData.createCarDOList();
        List<CarDTO> carDTOList = carMapper.makeCarDTOList(carDOList);
        assertThat(carDOList).isNotNull();
        assertThat(carDTOList.contains(carDOList));
    }

}