package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * All operations with a cars will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/{carId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CarDTO> getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return new ResponseEntity<>(CarMapper.makeCarDTO(carService.findCarById(carId)), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<CarDTO>> getAllCars()
    {
        List<CarDTO> carDTOList =  CarMapper.makeCarDTOList(carService.findAllCars());
        return new ResponseEntity<>(carDTOList, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) throws EntityNotFoundException, ConstraintsViolationException
    {
        return new ResponseEntity<>(CarMapper.makeCarDTO(carService.create(CarMapper.makeCarDO(carDTO))), HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateCar(@Valid @RequestBody CarDTO carDTO) throws EntityNotFoundException
    {
        carService.update(CarMapper.makeCarDO(carDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/{carId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
