package com.mytaxi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.controller.mapper.CarDriverMapper;
import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.ICarService;

/**
 * All operations with a cars will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{
    @Autowired
    private ICarService carService;

    @GetMapping(value = "/{carId}")
    public ResponseEntity<CarDTO> getCar(@Valid @PathVariable long carId) throws EntityNotFoundException
    {
        return new ResponseEntity<>(CarMapper.makeCarDTO(carService.findCarById(carId)), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<CarDriverDTO>> getAllCars()
    {
        List<CarDriverDTO> carDriverDTOList =  CarDriverMapper.makeCarDriverDTOList(carService.findAllCars());
        return new ResponseEntity<>(carDriverDTOList, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CarDTO> createCar(@Valid @RequestBody CarDTO carDTO) throws EntityNotFoundException, ConstraintsViolationException
    {
        return new ResponseEntity<>(CarMapper.makeCarDTO(carService.create(CarMapper.makeCarDO(carDTO))), HttpStatus.CREATED);
    }


    @PutMapping()
    public ResponseEntity<CarDTO> updateCar(@Valid @RequestBody CarDTO carDTO) throws EntityNotFoundException
    {
    	 return new ResponseEntity<>(CarMapper.makeCarDTO(carService.update(CarMapper.makeCarDO(carDTO))), HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{carId}")
    public ResponseEntity<Void> deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException,DriverMappedToACarException
    {
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
