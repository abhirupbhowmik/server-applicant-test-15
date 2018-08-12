package com.mytaxi.service.driver;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driverCar.DriverCarService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements DriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarService carService;

    @Autowired
    private DriverCarService driverCarService;

    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }


    @Override
    public DriverDTO selectCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {
        Object[] object = new Object[2];
        DriverDO driverDO;
        CarDO carDO;
        try
        {
            driverDO = findDriverChecked(driverId);
            carDO = carService.findCarById(carId);
            if (null != driverDO && null != carDO && OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus()))
            {
                DriverCarDO driverCarDO = new DriverCarDO();
                driverCarDO.setDriverId(driverDO.getId());
                driverCarDO.setCarId(carDO.getId());
                driverCarService.save(driverCarDO);
                object[0] = driverDO;
                object[1] = carDO;
            }
            else if (null != driverDO && null != carDO && OnlineStatus.OFFLINE.equals(driverDO.getOnlineStatus()))
            {
                throw new CarAlreadyInUseException("Currently driver is OFFLINE");
            }
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Exception while finding car or driver");
        }
        catch (DataIntegrityViolationException e)
        {
            throw new CarAlreadyInUseException("Car already in use by driver");
        }
        return DriverMapper.makeDriverCarDTO(object);

    }


    @Override
    public void removeCarByDriver(Long driverId, Long carId) throws CarAlreadyInUseException, EntityNotFoundException
    {
        DriverDO driverDO;
        CarDO carDO;
        try
        {
            driverDO = findDriverChecked(driverId);
            carDO = carService.findCarById(carId);
            if (null != driverDO && null != carDO && OnlineStatus.ONLINE.equals(driverDO.getOnlineStatus()))
            {
                DriverCarDO driverCarDO = driverCarService.findByDriverIdAndCarId(driverDO.getId(), carDO.getId());
                driverCarService.delete(driverCarDO);
            }
        }
        catch (EntityNotFoundException e)
        {
            throw new EntityNotFoundException("Exception while finding car or driver");
        }
        catch (InvalidDataAccessApiUsageException e)
        {
            throw new CarAlreadyInUseException("Car already removed by driver");
        }
    }


    @Override
    public List<DriverDTO> findDriverByCarAttributes(CarDTO carDTO)
    {
        List<DriverDTO> driverDataList = new ArrayList<>();
        List<Object[]> drivers = driverCarService.findDriverByCarAttributes(carDTO);
        drivers.forEach(object -> driverDataList.add(DriverMapper.makeDriverCarDTO(object)));
        return driverDataList;
    }
}
