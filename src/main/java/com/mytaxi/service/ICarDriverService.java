package com.mytaxi.service;

import java.util.List;

import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.datatransferobject.SearchDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;


public interface ICarDriverService
{
   
	List<DriverDO> searchDrivers(SearchDTO searchValue);
  
	CarDriverDTO selectCarByDriver(Long driverId,Long carId) throws CarAlreadyInUseException, EntityNotFoundException ,DriverMappedToACarException;

	CarDriverDTO removeCarByDriver(Long driverId,Long carId) throws CarAlreadyInUseException, EntityNotFoundException;


}
