package com.mytaxi.controller;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.domainvalue.SearchParameters;
import com.mytaxi.domainvalue.SearchType;
import com.mytaxi.exception.*;
import com.mytaxi.service.ICarDriverService;
import com.mytaxi.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController {
    @Autowired
    private IDriverService driverService;

    @Autowired
    private ICarDriverService driverCarService;


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException {
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@Valid @PathVariable long driverId) throws EntityNotFoundException, DriverMappedToACarException {
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateDriverLocation(
            @Valid @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
            throws EntityNotFoundException {
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDriversByStatus(@RequestParam OnlineStatus onlineStatus) {
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }

    @PostMapping("/{driverId}")
    public DriverDTO updateDriverStatus(@PathVariable long driverId, @RequestParam OnlineStatus onlineStatus)
            throws EntityNotFoundException, DriverMappedToACarException {
        return DriverMapper.makeDriverDTO(driverService.updateDriverStatus(driverId, onlineStatus));
    }


    @PostMapping("/{driverId}/{carId}")
    public CarDriverDTO mapDriverToCar(@PathVariable long driverId, @PathVariable long carId) throws EntityNotFoundException,
            CarAlreadyInUseException, DriverMappedToACarException {
        return driverCarService.selectCarByDriver(driverId, carId);
    }


    @DeleteMapping("/{driverId}/{carId}")
    public CarDriverDTO removeDriverToCarMap(@PathVariable long driverId, @PathVariable long carId) throws EntityNotFoundException,
            CarAlreadyInUseException {
        return driverCarService.removeCarByDriver(driverId, carId);
    }


    @GetMapping("/search/{searchValue}")
    public List<DriverDTO> searchDrivers(@RequestParam SearchType searchType, @RequestParam SearchParameters searchParameter,
                                         @PathVariable String searchValue) throws NoResultFoundException, InvalidSearchCriteria {
        return driverService.findDriverBySearchAttributes(searchType, searchParameter, searchValue);
    }


}
