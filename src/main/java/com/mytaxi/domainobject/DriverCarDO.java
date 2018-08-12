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

}
