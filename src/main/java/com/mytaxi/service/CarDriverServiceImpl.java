package com.mytaxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mytaxi.controller.mapper.CarDriverMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.datatransferobject.SearchDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.ICarDriverService;
import com.mytaxi.service.ICarService;
import com.mytaxi.service.IDriverService;

@Service
public class CarDriverServiceImpl implements ICarDriverService
{
   
    @Autowired
    private DriverRepository driverRepository;
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private ICarService carService;
    
    @Autowired
    private IDriverService driverService;

   
    @Override
    public CarDriverDTO selectCarByDriver(Long driverId,Long carId) throws CarAlreadyInUseException, EntityNotFoundException ,DriverMappedToACarException
    {
       DriverDO driverDO = driverService.find(driverId);
       CarDO  carDO = carService.findCarById(carId);
        if (null != driverDO && null != carDO && OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus()) && null == carDO.getDriver())
        {
        	CarDO mappedCar = carRepository.findCarMappedToDriver(driverDO.getId());
        	if(null == mappedCar) {
        		carDO.setDriver(driverDO);
            	carService.update(carDO);
        	}else {
        		throw new DriverMappedToACarException("Driver already mapped to a car.Driver cannot be mapped.");
        	}
        }
        else if (null != driverDO && null != carDO && OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus()) && null != carDO.getDriver())
        {
        	 throw new CarAlreadyInUseException("Car already in use by a driver.Cannot be selected.");
        }
        else if (null != driverDO && null != carDO && OnlineStatus.OFFLINE.equals(driverDO.getOnlineStatus()) )
        {
            throw new CarAlreadyInUseException("Currently driver is OFFLINE");
        }
        return CarDriverMapper.makeCarWithDriverDTO(carDO);
    }


    @Override
    public CarDriverDTO removeCarByDriver(Long driverId,Long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {
	    DriverDO driverDO = driverService.find(driverId);
	    CarDO  carDO = carService.findCarById(carId);
	    if (null != driverDO && null != carDO && OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus()))
        {
        	carDO.setDriver(null);
        	carService.update(carDO);
        }else if (null != driverDO && null != carDO && OnlineStatus.OFFLINE.equals(driverDO.getOnlineStatus()) )
         {
            throw new CarAlreadyInUseException("Currently driver is OFFLINE. Car canot be deselected.");
        }
       return CarDriverMapper.makeCarWithDriverDTO(carDO);
    }

	@Override
	public List<DriverDO> searchDrivers(SearchDTO searchValue) {
		return driverRepository.searchDrivers(searchValue);
	}




}
