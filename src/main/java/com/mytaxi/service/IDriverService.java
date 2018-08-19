package com.mytaxi.service;

import java.util.List;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.domainvalue.SearchParameters;
import com.mytaxi.domainvalue.SearchType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.InvalidSearchCriteria;
import com.mytaxi.exception.NoResultFoundException;

public interface IDriverService
{

    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException,DriverMappedToACarException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    DriverDO updateDriverStatus(long driverId,OnlineStatus onlineStatus) throws EntityNotFoundException, DriverMappedToACarException;
  
    List<DriverDTO> findDriverBySearchAttributes(SearchType searchType,SearchParameters searchParameter,String searchValue) throws NoResultFoundException,InvalidSearchCriteria;


}
