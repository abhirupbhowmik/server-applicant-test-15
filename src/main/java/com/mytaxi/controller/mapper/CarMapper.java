package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.CarDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper
{
    public static CarDO makeCarDO(CarDTO carDTO)
    {
        return new CarDO(carDTO.getTotalSeat(), carDTO.getCarType(), carDTO.getLicenseNo(), carDTO.getRating(), carDTO.getManufacturer());
    }


    public static CarDTO makeCarDTO(CarDO carDO)
    {
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
                .setId(carDO.getId())
                .setCarType(carDO.getCarType())
                .setLicenseNo(carDO.getLicenseNo())
                .setRating(carDO.getRating())
                .setManufacturer(carDO.getManufacturer());
        return carDTOBuilder.createCarDTO();
    }

    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars)
    {
        return cars.stream()
            .map(CarMapper::makeCarDTO)
            .collect(Collectors.toList());
    }
}
