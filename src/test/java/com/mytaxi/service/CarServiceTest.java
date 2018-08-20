package com.mytaxi.service;

import com.google.common.collect.Lists;
import com.mytaxi.TestData;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private CarServiceImpl carService;


    @BeforeClass
    public static void setUp() {
        MockitoAnnotations.initMocks(ICarService.class);
    }


    @Test
    public void testFindCarById() throws EntityNotFoundException {
        CarDO carDO = TestData.createCarDO();
        when(carRepository.findById(carDO.getId())).thenReturn(Optional.ofNullable(carDO));
        carRepository.save(carDO);
        CarDO createdCarDO = carService.findCarById(carDO.getId());
        assertThat(carDO).isEqualTo(createdCarDO);
        assertEquals(createdCarDO.getId(), carDO.getId());
        verify(carRepository, times(1)).findById(carDO.getId());
    }


    @Test
    public void testFindAllCars() {
        List<CarDO> listOfCarDO = TestData.createCarDOList();
        when(carRepository.findAll()).thenReturn(listOfCarDO);
        listOfCarDO.forEach((car) -> carRepository.save(car));
        List<CarDO> createdListOfCarDO = carService.findAllCars();
        assertThat(createdListOfCarDO).isEqualTo(listOfCarDO);
        verify(carRepository, times(1)).findAll();
    }


    @Test
    public void testCreateCar() throws EntityNotFoundException, ConstraintsViolationException {
        CarDO carDO = TestData.createCarDO();
        DriverDO driverDO = carDO.getDriver();
        when(carRepository.save(carDO)).thenReturn(carDO);
        when(driverRepository.findById(driverDO.getId())).thenReturn(Optional.ofNullable(driverDO));
        CarDO createdCarDO = carService.create(carDO);
        assertThat(createdCarDO).isEqualTo(carDO);
        assertThat(createdCarDO.getDriver()).isEqualTo(driverDO);
        verify(carRepository, times(1)).save(carDO);
    }


    @Test
    public void testUpdateCar() throws EntityNotFoundException, ConstraintsViolationException {
        CarDO carDO = TestData.createCarDO();
        DriverDO driverDO = carDO.getDriver();
        when(carRepository.save(carDO)).thenReturn(carDO);
        when(driverRepository.findById(driverDO.getId())).thenReturn(Optional.ofNullable(driverDO));
        carService.create(carDO);
        carDO.setLicenseNo("WB-2234");
        CarDO updatedCarDO = carService.create(carDO);
        assertThat(updatedCarDO).isEqualTo(carDO);
        assertThat(updatedCarDO.getDriver()).isEqualTo(driverDO);
        verify(carRepository, times(2)).save(carDO);
    }

    @Test(expected = DriverMappedToACarException.class)
    public void shouldNotDeleteCarWhenDriverIsMapped() throws EntityNotFoundException, DriverMappedToACarException {
        CarDO carDO = TestData.createCarDO();
        given(carRepository.findById(carDO.getId())).willReturn(Optional.ofNullable(carDO));
        carService.delete(carDO.getId());
        verify(carRepository, Mockito.only()).findById(carDO.getId());
    }

    @Test
    public void shouldDeleteCar() throws EntityNotFoundException, DriverMappedToACarException {
        CarDO car_1 = TestData.createCarDOWithoutDriver();
        CarDO car_2 = TestData.createCarDOWithoutDriver();
        CarDO car_3 = TestData.createCarDOWithoutDriver();
        car_1.setId(1L);
        car_2.setId(2L);
        car_3.setId(3L);
        car_2.setLicenseNo("WB-2234");
        car_3.setLicenseNo("WB-3234");
        List<CarDO> listOfCarsDO = new ArrayList<>();
        listOfCarsDO.add(car_1);
        listOfCarsDO.add(car_2);
        listOfCarsDO.add(car_3);
        when(carRepository.findAll()).thenReturn(listOfCarsDO);
        listOfCarsDO.forEach((car) -> carRepository.save(car));
        int carCount = Lists.newArrayList(carRepository.findAll()).size();
        given(carRepository.findById(car_1.getId())).willReturn(Optional.ofNullable(car_1));
        carService.delete(car_1.getId());
        given(carService.findCarById(car_1.getId())).willReturn(null);
        verify(carRepository, times(1)).findById(car_1.getId());
    }
}

