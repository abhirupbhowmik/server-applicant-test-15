package com.mytaxi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;

@Service
public class CarServiceImpl implements ICarService
{
    private static Logger LOG = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

   

    @Override
    public CarDO findCarById(final Long carId) throws EntityNotFoundException
    {
        return checkCarAvailability(carId);
    }


    @Override
    public List<CarDO> findAllCars()
    {
        return (List<CarDO>) carRepository.findAll();
    }


    @Override
    public CarDO create(CarDO carDO) throws EntityNotFoundException, ConstraintsViolationException
    {
        CarDO car;
        try {
            car = carRepository.save(carDO);
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            LOG.warn("Exception occurred while creating new car", dataIntegrityViolationException);
            throw new ConstraintsViolationException(dataIntegrityViolationException.getMessage());
        }

        return car;
    }
    
    @Override
    @Transactional
    public CarDO update(CarDO car) throws EntityNotFoundException
    {
        checkCarAvailability(car.getId());
        return carRepository.save(car);
        
    }


    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException,DriverMappedToACarException
    {
        CarDO car = checkCarAvailability(carId);
        if(null == car.getDriver()) {
        	carRepository.delete(car);
        }else {
        	throw new DriverMappedToACarException("Driver is mapped to car.Car cannot be deleted");
        }
    }


    private CarDO checkCarAvailability(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Not able to find car with id = " + carId));
    }


  
}
