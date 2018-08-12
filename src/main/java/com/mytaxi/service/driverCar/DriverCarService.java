package com.mytaxi.service.driverCar;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.DriverCarDO;

import java.util.List;


public interface DriverCarService
{
    void delete(DriverCarDO driverCar);

    DriverCarDO  save(DriverCarDO driverCar);

    DriverCarDO findByDriverIdAndCarId(final Long driverId, final Long carId);

    List<Object[]> findDriverByCarAttributes(final CarDTO carDTO);


}
