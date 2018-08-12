package com.mytaxi.dataaccessobject;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverCarRepository extends CrudRepository<DriverCarDO, Long>
{
    DriverCarDO findByDriverIdAndCarId(final Long driverId, final Long carId);

    @Query("SELECT C, D FROM Car C, Driver D, DriverCar DC WHERE DC.carId = C.id AND D.id = DC.driverId AND (C.totalSeat = :#{#carData.totalSeat} OR C.rating = :#{#carData.rating} OR C.carType = :#{#carData.carType})"
    )
    List<Object[]> findDriverByCarAttributes(@Param("carData") final CarDTO carDTO);

}
