package com.mytaxi.domainobject;

import javax.persistence.*;

@Entity
@Table(name = "driver_car")
public class DriverCarDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id", unique = true)
    private Long driverId;

    @Column(name = "car_id", unique = true)
    private Long carId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public DriverCarDO() {
    }

    public DriverCarDO(Long id, Long driverId, Long carId) {
        this.id = id;
        this.driverId = driverId;
        this.carId = carId;
    }
}
