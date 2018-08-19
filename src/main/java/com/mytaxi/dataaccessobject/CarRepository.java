package com.mytaxi.dataaccessobject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mytaxi.domainobject.CarDO;

/**
 * Database Access Object for car table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{
	 @Query(nativeQuery = true, value="SELECT C.* FROM  driver D,car C WHERE D.driver_id = C.driver_id  AND D.driver_id= :driverId"
	    	    )
	    CarDO findCarMappedToDriver(@Param("driverId")  long driverId);
}
