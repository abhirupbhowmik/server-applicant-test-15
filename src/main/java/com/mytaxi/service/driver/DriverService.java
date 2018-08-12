package com.mytaxi.service.driver;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

public interface DriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    DriverDTO selectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException;

    void removeCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException;

    List<DriverDTO> findDriverByCarAttributes(CarDTO carDTO);

}
