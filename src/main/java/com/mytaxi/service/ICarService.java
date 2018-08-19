package com.mytaxi.service;

import java.util.List;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;


public interface ICarService
{

    CarDO findCarById(final Long carId) throws EntityNotFoundException;

    List<CarDO> findAllCars();

    CarDO create(final CarDO car) throws EntityNotFoundException, ConstraintsViolationException;

    CarDO update(final CarDO car) throws EntityNotFoundException;

    void delete(final Long carId) throws EntityNotFoundException,DriverMappedToACarException;


}
