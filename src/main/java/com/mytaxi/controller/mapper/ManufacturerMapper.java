package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.ManufacturerDO;

import java.util.List;
import java.util.stream.Collectors;

public class ManufacturerMapper
{
    public static ManufacturerDO makeManufacturerDO(ManufacturerDTO manufacturerDTO)
    {
        return new ManufacturerDO(manufacturerDTO.getName(), manufacturerDTO.getCountry());
    }


    public static ManufacturerDTO makeManufacturerDTO(ManufacturerDO manufacturerDO)
    {
        ManufacturerDTO.ManufacturerDTOBuilder manufacturerDTOBuilder = ManufacturerDTO.newBuilder()
                .setId(manufacturerDO.getId())
                .setCountry(manufacturerDO.getCountry());
        return manufacturerDTOBuilder.createManufacturerDTO();
    }

    public static List<ManufacturerDTO> makeManufacturerDTOList(List<ManufacturerDO> manufacturers)
    {
        return manufacturers.stream()
            .map(ManufacturerMapper::makeManufacturerDTO)
            .collect(Collectors.toList());
    }
}
