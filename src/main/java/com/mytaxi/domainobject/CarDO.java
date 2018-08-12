package com.mytaxi.domainobject;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "car")
public class CarDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "total_seat")
    @NotNull(message = "Total Seat nos cannot be null")
    private Integer totalSeat;

    @Column(name = "car_type")
    private String carType;

    @Column(name = "purchase_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime purchaseDate = ZonedDateTime.now();

    @Column(nullable = false, name = "license_no")
    @NotNull(message = "License No cannot be null")
    private String licenseNo;

    @Column(name = "rating")
    private Float rating;

    @OneToOne
    @JoinColumn(name="manufacturer")
    private ManufacturerDO manufacturer;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(Integer totalSeat) {
        this.totalSeat = totalSeat;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public ManufacturerDO getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerDO manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    private CarDO ()
    {
    }

    public CarDO(Integer totalSeat, String carType, String licenseNo, Float rating, ManufacturerDO manufacturer) {
        this.totalSeat = totalSeat;
        this.carType = carType;
        this.purchaseDate = purchaseDate;
        this.licenseNo = licenseNo;
        this.rating = rating;
        this.manufacturer = manufacturer;
        this.deleted = false;
    }
}
