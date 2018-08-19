package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;

public class CarDriverMapper {

	  public static CarDriverDTO makeCarWithDriverDTO(CarDO carDO)
	    {
		   CarDriverDTO carDriverDTO = new CarDriverDTO();
		   carDriverDTO.setCarDetails(CarMapper.makeCarDTO(carDO));
      	   DriverDO driverDO = carDO.getDriver();
	        if(null != driverDO) {
	        	carDriverDTO.setDriverDetails(DriverMapper.makeDriverDTO(driverDO));
	        }
	       return carDriverDTO;
	    }
	  
	  public static List<CarDriverDTO> makeCarDriverDTOList(Collection<CarDO> cars)
	    {
	        return cars.stream()
	            .map(CarDriverMapper::makeCarWithDriverDTO)
	            .collect(Collectors.toList());
	    }
}
