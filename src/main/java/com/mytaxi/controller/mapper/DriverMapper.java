package com.mytaxi.controller.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;

public class DriverMapper
{
    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverDO(driverDTO.getId(), driverDTO.getUsername(), driverDTO.getPassword(), driverDTO.getStatus());
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
    	DriverDTO.DriverDTOBuilder driverDTOBuilder =null;
    	DriverDTO driverDTO = null;
    	if(null!= driverDO ) {
    	driverDTOBuilder = DriverDTO.newBuilder()
                .setId(driverDO.getId())
                .setPassword(driverDO.getPassword())
                .setUsername(driverDO.getUsername())
                .setStatus(driverDO.getOnlineStatus());

            GeoCoordinate coordinate = driverDO.getCoordinate();
            if (coordinate != null)
            {
                driverDTOBuilder.setCoordinate(coordinate);
            }
            driverDTO =  driverDTOBuilder.createDriverDTO();
    	}
    	return driverDTO;
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }

   
}
