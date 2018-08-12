package com.mytaxi.service.driverCar;

import com.mytaxi.dataaccessobject.DriverCarRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.DriverCarDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultDriverCarService implements DriverCarService
{
    private static Logger LOG = LoggerFactory.getLogger(DefaultDriverCarService.class);

    @Autowired
    private DriverCarRepository driverCarRepository;

    @Override
    public void delete(DriverCarDO driverCarDO)
    {
        driverCarRepository.delete(driverCarDO);
    }


    @Override
    public DriverCarDO save(DriverCarDO driverCarDO)
    {
        return driverCarRepository.save(driverCarDO);
    }


    @Override
    public DriverCarDO findByDriverIdAndCarId(Long driverId, Long carId)
    {
        return driverCarRepository.findByDriverIdAndCarId(driverId, carId);
    }


    public List<Object[]> findDriverByCarAttributes(CarDTO carDTO)
    {
        return driverCarRepository.findDriverByCarAttributes(carDTO);
    }
}
