package com.mytaxi.service;

import com.google.common.collect.Lists;
import com.mytaxi.TestData;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private DriverServiceImpl driverService;


    @BeforeClass
    public static void setUp() {
        MockitoAnnotations.initMocks(ICarService.class);
    }


    @Test
    public void testFindDriverById() throws EntityNotFoundException {
        DriverDO driverDO = TestData.createOnlineDriverDO();
        when(driverRepository.findById(driverDO.getId())).thenReturn(Optional.ofNullable(driverDO));
        driverRepository.save(driverDO);
        DriverDO createdDriverDO = driverService.find(driverDO.getId());
        assertThat(driverDO).isEqualTo(createdDriverDO);
        assertEquals(createdDriverDO.getId(), driverDO.getId());
        verify(driverRepository, times(1)).findById(driverDO.getId());
    }


    @Test
    public void testCreateDriver() throws ConstraintsViolationException {
        DriverDO driverDO = TestData.createOnlineDriverDO();
        when(driverRepository.save(driverDO)).thenReturn(driverDO);
        DriverDO createdDriverDO = driverService.create(driverDO);
        assertThat(createdDriverDO).isEqualTo(driverDO);
        verify(driverRepository, times(1)).save(driverDO);
    }


    @Test
    public void testUpdateDriver() throws EntityNotFoundException, ConstraintsViolationException {
        DriverDO driverDO = TestData.createOnlineDriverDO();
        when(driverRepository.save(driverDO)).thenReturn(driverDO);
        driverService.create(driverDO);
        driverDO.setUsername("TestOnlineDriverUpdated");
        DriverDO updatedDriverDO = driverService.create(driverDO);
        assertThat(updatedDriverDO).isEqualTo(driverDO);
        verify(driverRepository, times(2)).save(driverDO);
    }

    @Test
    public void shouldDeleteDriver() throws EntityNotFoundException, DriverMappedToACarException {
        DriverDO driver_1 = TestData.createOnlineDriverDO();
        DriverDO driver_2 = TestData.createOnlineDriverDO();
        DriverDO driver_3 = TestData.createOnlineDriverDO();
        driver_1.setId(1L);
        driver_2.setId(2L);
        driver_3.setId(3L);
        List<DriverDO> listOfDriverDO = new ArrayList<>();
        listOfDriverDO.add(driver_1);
        listOfDriverDO.add(driver_2);
        listOfDriverDO.add(driver_3);
        when(driverRepository.findAll()).thenReturn(listOfDriverDO);
        listOfDriverDO.forEach((driver) -> driverRepository.save(driver));
        int driverCount = Lists.newArrayList(driverRepository.findAll()).size();
        given(driverRepository.findById(driver_1.getId())).willReturn(Optional.ofNullable(driver_1));
        driverService.delete(driver_1.getId());
        given(driverService.find(driver_1.getId())).willReturn(null);
        verify(driverRepository, times(1)).findById(driver_1.getId());
    }
}

