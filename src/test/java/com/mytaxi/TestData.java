package com.mytaxi;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

import java.util.ArrayList;
import java.util.List;

import static java.time.ZonedDateTime.now;

public class TestData {

    public static CarDO createCarDO() {

        CarDO carDO = new CarDO();
        carDO.setId(10L);
        carDO.setLicenseNo("WB-1234");
        carDO.setCarType(EngineType.ELECTRIC);
        carDO.setManufacturer("BMW");
        carDO.setPurchaseDate(now());
        carDO.setRating("8");
        carDO.setTotalSeat("5");
        carDO.setDriver(createOnlineDriverDO());
        return carDO;
    }

    public static CarDO createCarDOWithoutDriver() {

        CarDO carDO = new CarDO();
        carDO.setLicenseNo("WB-1234");
        carDO.setCarType(EngineType.ELECTRIC);
        carDO.setManufacturer("BMW");
        carDO.setRating("8");
        carDO.setTotalSeat("5");
        return carDO;
    }

    public static DriverDO createOnlineDriverDO() {

        DriverDO driverDO = new DriverDO();
        driverDO.setUsername("TestOnlineDriver");
        driverDO.setPassword("TestOnlineDriverPwd");
        driverDO.setOnlineStatus(OnlineStatus.ONLINE);
        GeoCoordinate geoCoordinate = new GeoCoordinate(50, 50);
        driverDO.setCoordinate(geoCoordinate);
        return driverDO;
    }

    public static DriverDO createOfflineDriverDO() {

        DriverDO driverDO = new DriverDO();
        driverDO.setId(6L);
        driverDO.setUsername("TestOfflineDriver");
        driverDO.setPassword("TestOfflineDriverPwd");
        driverDO.setOnlineStatus(OnlineStatus.OFFLINE);
        GeoCoordinate geoCoordinate = new GeoCoordinate(60, 60);
        driverDO.setCoordinate(geoCoordinate);
        return driverDO;
    }

    public static CarDriverDTO createCarDriverDTO() {

        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
                .setId(10L)
                .setLicenseNo("WB-1234")
                .setCarType(EngineType.ELECTRIC)
                .setManufacturer("BMW")
                .setRating("8")
                .setTotalSeat("5");
        CarDTO carDTO = carDTOBuilder.createCarDTO();

        CarDriverDTO carDriverDTO = new CarDriverDTO();
        carDriverDTO.setCarDetails(carDTO);
        DriverDTO driverDTO = createDriverDTO();
        carDriverDTO.setDriverDetails(driverDTO);

        return carDriverDTO;

    }

    public static CarDTO createCarDTO() {
        return CarDTO.newBuilder()
                .setId(10L)
                .setLicenseNo("WB-1234")
                .setCarType(EngineType.ELECTRIC)
                .setManufacturer("BMW")
                .setRating("8")
                .setTotalSeat("5")
                .createCarDTO();

    }


    public static DriverDTO createDriverDTO() {
        return DriverDTO.newBuilder()
                .setId(5L)
                .setUsername("TestOnlineDriver")
                .setPassword("TestOnlineDriverPwd")
                .setStatus(OnlineStatus.ONLINE)
                .setCoordinate(new GeoCoordinate(50, 50))
                .createDriverDTO();

    }

    public static List<CarDTO> createCarDTOList() {

        List<CarDTO> carDTOList = new ArrayList<>();
        carDTOList.add(createCarDTO());
        carDTOList.add(createCarDTO());
        carDTOList.add(createCarDTO());

        return carDTOList;
    }

    public static List<CarDO> createCarDOList() {

        List<CarDO> carDOList = new ArrayList<>();
        carDOList.add(createCarDO());
        carDOList.add(createCarDO());
        carDOList.add(createCarDO());

        return carDOList;
    }

    public static List<DriverDTO> createDriverDTOList() {

        List<DriverDTO> driverDTOList = new ArrayList<>();
        driverDTOList.add(createDriverDTO());
        driverDTOList.add(createDriverDTO());
        driverDTOList.add(createDriverDTO());

        return driverDTOList;
    }

    public static List<DriverDO> createDriverDOList() {

        List<DriverDO> driverDOList = new ArrayList<>();
        driverDOList.add(createOnlineDriverDO());
        driverDOList.add(createOnlineDriverDO());
        driverDOList.add(createOnlineDriverDO());

        return driverDOList;
    }

}
