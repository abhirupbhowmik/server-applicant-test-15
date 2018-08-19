package com.mytaxi.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.datatransferobject.SearchDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.domainvalue.SearchParameters;
import com.mytaxi.domainvalue.SearchType;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DriverMappedToACarException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.exception.InvalidSearchCriteria;
import com.mytaxi.exception.NoResultFoundException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DriverServiceImpl implements IDriverService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DriverServiceImpl.class);

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICarDriverService driverCarService;

	

    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDO find(Long driverId) throws EntityNotFoundException
    {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException
    {
        DriverDO driver;
        try
        {
            driver = driverRepository.save(driverDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("Some constraints are thrown due to driver creation", e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws EntityNotFoundException,DriverMappedToACarException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        CarDO carDO = carRepository.findCarMappedToDriver(driverDO.getId());
        if(null != carDO) {
        	throw new DriverMappedToACarException("Driver already mapped to a car.Driver cannot be deleted.");
        }else {
        	 driverRepository.delete(driverDO);
        }
       
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException
    {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
        driverRepository.save(driverDO);
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    public List<DriverDO> find(OnlineStatus onlineStatus)
    {
        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException
    {
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }


    

    @Override
    public List<DriverDTO> findDriverBySearchAttributes(SearchType searchType,SearchParameters searchParameter,String searchValue) throws NoResultFoundException,InvalidSearchCriteria
    {
        List<DriverDTO> driverDataList = new ArrayList<>();
        List<DriverDO> drivers = null;
        String searchTypeCode = searchType.getSearchTypCode();
        String searchParameterCode = searchParameter.getSearchParameterCode();
        if(!searchTypeCode.equalsIgnoreCase(searchParameterCode)) {
    		throw new InvalidSearchCriteria("The selected Search type  : "+searchType+" and the corresponding Search parameter : "+searchParameter+" do not match");
    	}
       try {
    		String searchParamArgs[] = searchParameter.name().split("_");
    		SearchDTO  searchDTO = new SearchDTO();
			BeanUtils.setProperty(searchDTO, searchParamArgs[1], searchValue);
			drivers= driverCarService.searchDrivers(searchDTO);
    	} catch (IllegalAccessException | InvocationTargetException e) {
			
		}
       drivers.forEach(driver -> driverDataList.add(DriverMapper.makeDriverDTO(driver)));
       if(CollectionUtils.isEmpty(drivers)){
        	throw new NoResultFoundException("No Driver details found for the Search criteria : "+searchParameter+" and Search value : "+searchValue);
        }
        return driverDataList;
    }


   

	@Override
	public DriverDO updateDriverStatus(long driverId,OnlineStatus onlineStatus) throws EntityNotFoundException, DriverMappedToACarException {
		DriverDO driverDO = find(driverId);
		if(OnlineStatus.ONLINE.equals(onlineStatus)){
			CarDO carDO = carRepository.findCarMappedToDriver(driverId);
			if(null == carDO) {
				driverDO.setOnlineStatus(onlineStatus);
			}else {
				 throw new DriverMappedToACarException("Driver already mapped to a car.Driver cannot be made offline.");
			}
		}else if(OnlineStatus.OFFLINE.equals(onlineStatus)) {
			driverDO.setOnlineStatus(onlineStatus);
		}
		return driverRepository.save(driverDO);
		
	}
}
