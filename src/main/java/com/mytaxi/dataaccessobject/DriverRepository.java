package com.mytaxi.dataaccessobject;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mytaxi.datatransferobject.SearchDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
    
    		   
    @Query(nativeQuery = true, value="SELECT D.* FROM  driver D,car C WHERE D.driver_id = C.driver_id AND (C.rating = :#{#searchValue.rating}  OR C.car_type = :#{#searchValue.carType} OR C.license_no LIKE :#{#searchValue.licenseNo}  OR C.total_seat = :#{#searchValue.totalSeat}  OR C.manufacturer LIKE :#{#searchValue.manufacturer} OR D.online_status = :#{#searchValue.onlineStatus} OR D.username LIKE :#{#searchValue.username} )"
    )
    List<DriverDO> searchDrivers(@Param("searchValue")  SearchDTO searchValue);
  
}
