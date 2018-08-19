package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.domainvalue.SearchParameters;
import com.mytaxi.domainvalue.SearchType;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.InvalidSearchCriteria;
import com.mytaxi.exception.NoResultFoundException;
import com.mytaxi.service.ICarDriverService;
import com.mytaxi.service.IDriverService;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController
{
    @Autowired
    private IDriverService driverService;
    
    @Autowired
    private ICarDriverService driverCarService;


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException
    {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException
    {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException,DriverMappedToACarException
    {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateDriverLocation(
        @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDriversByStatus(@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException
    {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }

    @PostMapping("/{driverId}")
    public DriverDTO updateDriverStatus(@PathVariable long driverId,@RequestParam OnlineStatus onlineStatus)
        throws ConstraintsViolationException, EntityNotFoundException,DriverMappedToACarException
    {
        return DriverMapper.makeDriverDTO(driverService.updateDriverStatus(driverId,onlineStatus));
    }


    @PostMapping("/{driverId}/{carId}")
    public CarDriverDTO mapDriverToCar(@PathVariable long driverId,@PathVariable long carId) throws EntityNotFoundException,
            CarAlreadyInUseException ,DriverMappedToACarException
    {
        return driverCarService.selectCarByDriver(driverId,carId);
    }


    @DeleteMapping("/{driverId}/{carId}")
    public CarDriverDTO removeDriverToCarMap(@PathVariable long driverId,@PathVariable long carId) throws EntityNotFoundException,
            CarAlreadyInUseException
    {
    	return driverCarService.removeCarByDriver(driverId,carId);
    }


    @GetMapping("/search/{searchValue}")
    public List<DriverDTO> searchDrivers( @RequestParam SearchType searchType,@RequestParam SearchParameters searchParameter,
    		@PathVariable String searchValue) throws NoResultFoundException ,InvalidSearchCriteria
    {
    	return driverService.findDriverBySearchAttributes(searchType,searchParameter,searchValue);
    }


}
