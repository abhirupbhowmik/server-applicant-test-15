package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultCarService implements CarService
{
    private static Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;


    @Override
    public CarDO findCarById(final Long carId) throws EntityNotFoundException
    {
        return checkCarAvailability(carId);
    }


    @Override
    public List<CarDO> findAllCars()
    {
        return (List<CarDO>) carRepository.findAll();
    }


    @Override
    public CarDO create(CarDO carDO) throws EntityNotFoundException, ConstraintsViolationException
    {
        CarDO car;
        try {
            car = carRepository.save(carDO);
        }catch (DataIntegrityViolationException dataIntegrityViolationException){
            LOG.warn("Exception occured while creating new car", dataIntegrityViolationException);
            throw new ConstraintsViolationException(dataIntegrityViolationException.getMessage());
        }

        return car;
    }

    @Override
    @Transactional
    public void update(CarDO car) throws EntityNotFoundException
    {
        CarDO updateCar = checkCarAvailability(car.getId());
        updateCar.setManufacturer(checkManufacturer(car));
        updateCar.setRating(car.getRating());
        updateCar.setTotalSeat(car.getTotalSeat());
        updateCar.setDeleted(car.getDeleted());
    }


    @Override
    @Transactional
    public void delete(Long carId) throws EntityNotFoundException
    {
        CarDO car = checkCarAvailability(carId);
        car.setDeleted(Boolean.TRUE);
    }


    private CarDO checkCarAvailability(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Not able to find car with id = " + carId));
    }


    private ManufacturerDO checkManufacturer(CarDO car) throws EntityNotFoundException
    {
        Long manufacturerId = car.getManufacturer().getId();
        return manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new EntityNotFoundException("Not able to find manufacturer with id = " + manufacturerId));
    }
}
