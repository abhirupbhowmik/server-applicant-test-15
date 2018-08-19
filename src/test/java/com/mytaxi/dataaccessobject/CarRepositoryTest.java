package com.mytaxi.dataaccessobject;

import com.google.common.collect.Lists;
import com.mytaxi.TestData;
import com.mytaxi.domainobject.CarDO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CarRepositoryTest extends AbstractRepositoryTest {

	@Autowired
	private CarRepository carRepository;


	@Test
	public void shouldGetCarById() {
		CarDO car_1 = TestData.createCarDOWithoutDriver();
		Assert.assertEquals(carRepository.save(car_1).getManufacturer(), car_1.getManufacturer());
	}

	@Test
	public void shouldGetAllCars() {
		CarDO car_1 = TestData.createCarDOWithoutDriver();
		CarDO car_2 = TestData.createCarDOWithoutDriver();
		CarDO car_3 = TestData.createCarDOWithoutDriver();
		car_2.setLicenseNo("WB-2234");
		car_3.setLicenseNo("WB-3234");
		List<CarDO> listOfCarsDO = new ArrayList<>();
		listOfCarsDO.add(car_1);
		listOfCarsDO.add(car_2);
		listOfCarsDO.add(car_3);
		listOfCarsDO.forEach((car) -> carRepository.save(car));
		ArrayList<CarDO> cars = Lists.newArrayList(carRepository.findAll());
		assertThat(cars).containsAll(listOfCarsDO);
	}

	@Test
	public void shouldCreateCar() {
		CarDO carDO = TestData.createCarDOWithoutDriver();
		CarDO createdCarDO = carRepository.save(carDO);
		Assert.assertEquals(createdCarDO.getManufacturer(), carDO.getManufacturer());

	}

	@Test
	public void shouldUpdateCars() {
		CarDO carDO = TestData.createCarDOWithoutDriver();
		carRepository.save(carDO);
		carDO.setManufacturer("Setra");
		CarDO updatedCarDO = carRepository.save(carDO);
		Assert.assertEquals(updatedCarDO.getManufacturer(), carDO.getManufacturer());

	}

	@Test
	public void shouldDeleteCars() {
		CarDO car_1 = TestData.createCarDOWithoutDriver();
		CarDO car_2 = TestData.createCarDOWithoutDriver();
		CarDO car_3 = TestData.createCarDOWithoutDriver();
		car_2.setLicenseNo("WB-2234");
		car_3.setLicenseNo("WB-3234");
		List<CarDO> listOfCarsDO = new ArrayList<>();
		listOfCarsDO.add(car_1);
		listOfCarsDO.add(car_2);
		listOfCarsDO.add(car_3);
		listOfCarsDO.forEach((car) -> carRepository.save(car));
		int carCount = Lists.newArrayList(carRepository.findAll()).size();
		Assert.assertEquals(carCount, listOfCarsDO.size());
		carRepository.delete(car_1);
		Assert.assertEquals(Lists.newArrayList(carRepository.findAll()).size(), carCount-1);
	}

}