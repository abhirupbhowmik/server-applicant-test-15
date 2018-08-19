package com.mytaxi.dataaccessobject;

import com.google.common.collect.Lists;
import com.mytaxi.TestData;
import com.mytaxi.domainobject.DriverDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DriverRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private DriverRepository driverRepository;


    @Test
    public void shouldGetCarById() {
        DriverDO driver_1 = TestData.createOnlineDriverDO();
        Assert.assertEquals(driverRepository.save(driver_1).getUsername(), driver_1.getUsername());
    }

    @Test
    public void shouldGetAllDrivers() {
        DriverDO driver_1 = TestData.createOnlineDriverDO();
        DriverDO driver_2 = TestData.createOnlineDriverDO();
        DriverDO driver_3 = TestData.createOnlineDriverDO();
        driver_2.setUsername("TestOnlineDriver2");
        driver_3.setUsername("TestOnlineDriver3");
        List<DriverDO> listOfDriversDO = new ArrayList<>();
        listOfDriversDO.add(driver_1);
        listOfDriversDO.add(driver_2);
        listOfDriversDO.add(driver_3);
        listOfDriversDO.forEach((driver) -> driverRepository.save(driver));
        ArrayList<DriverDO> drivers = Lists.newArrayList(driverRepository.findAll());
        assertThat(drivers).containsAll(listOfDriversDO);
    }

    @Test
    public void shouldCreateDriver() {
        DriverDO driverDO = TestData.createOnlineDriverDO();
        DriverDO createdDriverDO = driverRepository.save(driverDO);
        Assert.assertEquals(createdDriverDO.getUsername(), driverDO.getUsername());

    }

    @Test
    public void shouldUpdateDrivers() {
        DriverDO driverDO = TestData.createOnlineDriverDO();
        driverRepository.save(driverDO);
        driverDO.setUsername("TestOnlineDriverUpdated");
        DriverDO updatedDriverDO = driverRepository.save(driverDO);
        Assert.assertEquals(updatedDriverDO.getUsername(), driverDO.getUsername());

    }

    @Test
    public void shouldDeleteDrivers() {

        DriverDO driver_1 = TestData.createOnlineDriverDO();
        DriverDO driver_2 = TestData.createOnlineDriverDO();
        DriverDO driver_3 = TestData.createOnlineDriverDO();
        driver_2.setUsername("TestOnlineDriver2");
        driver_3.setUsername("TestOnlineDriver3");
        List<DriverDO> listOfDriversDO = new ArrayList<>();
        listOfDriversDO.add(driver_1);
        listOfDriversDO.add(driver_2);
        listOfDriversDO.add(driver_3);
        listOfDriversDO.forEach((car) -> driverRepository.save(car));
        ArrayList<DriverDO> drivers = Lists.newArrayList(driverRepository.findAll());
        assertThat(drivers).containsAll(listOfDriversDO);
        int driverCount = Lists.newArrayList(driverRepository.findAll()).size();
        Assert.assertEquals(driverCount, listOfDriversDO.size());
        driverRepository.delete(driver_1);
        Assert.assertEquals(Lists.newArrayList(driverRepository.findAll()).size(), driverCount-1);
    }

}
