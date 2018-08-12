package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;


public interface CarService
{

    CarDO findCarById(final Long carId) throws EntityNotFoundException;

    List<CarDO> findAllCars();

    CarDO create(final CarDO car) throws EntityNotFoundException, ConstraintsViolationException;

    void update(final CarDO car) throws EntityNotFoundException;

    void delete(final Long carId) throws EntityNotFoundException;


}
