package com.mytaxi.domainobject;

import com.mytaxi.domainvalue.EngineType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "car",
        uniqueConstraints = @UniqueConstraint(name = "license_no", columnNames = {"license_no"})
)
public class CarDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;

    @Column(nullable = false, name = "total_seat")
    @NotNull(message = "Total Seat nos cannot be null")
    private String totalSeat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "car_type")
    private EngineType carType;

    @Column(name = "purchase_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime purchaseDate = ZonedDateTime.now();

    @Column(nullable = false, name = "license_no")
    @NotNull(message = "License No cannot be null")
    private String licenseNo;

    @Column(name = "rating")
    private String rating;

    @OneToOne
    @JoinColumn(nullable = true, name = "driver_id")
    private DriverDO driver;


    @Column(name = "manufacturer")
    private String manufacturer;


    public CarDO(@NotNull(message = "Total Seat nos cannot be null") String totalSeat, EngineType carType,
                 @NotNull(message = "License No cannot be null") String licenseNo, String rating,
                 String manufacturer, Long id) {
        super();
        this.id = id;
        this.totalSeat = totalSeat;
        this.carType = carType;
        this.licenseNo = licenseNo;
        this.rating = rating;
        this.manufacturer = manufacturer;
    }

    public CarDO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(String totalSeat) {
        this.totalSeat = totalSeat;
    }

    public EngineType getCarType() {
        return carType;
    }

    public void setCarType(EngineType carType) {
        this.carType = carType;
    }

    public ZonedDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(ZonedDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public DriverDO getDriver() {
        return driver;
    }

    public void setDriver(DriverDO driver) {
        this.driver = driver;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }


}
