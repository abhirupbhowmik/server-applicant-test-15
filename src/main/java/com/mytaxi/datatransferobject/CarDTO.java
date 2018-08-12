package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainobject.ManufacturerDO;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "Seat numbers can not be null!")
    private Integer totalSeat;

    private String carType;

    private Float rating;

    @NotNull(message = "Car numbers can not be null!")
    private String licenseNo;

    private ManufacturerDO manufacturer;

    private CarDTO() {
    }

    public CarDTO(Long id, Integer totalSeat, String carType, Float rating, String licenseNo, ManufacturerDO manufacturer) {
        this.id = id;
        this.totalSeat = totalSeat;
        this.carType = carType;
        this.rating = rating;
        this.licenseNo = licenseNo;
        this.manufacturer = manufacturer;
    }

    public static CarDTOBuilder newBuilder() {
        return new CarDTOBuilder();
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public String getCarType() { return carType; }

    public Float getRating() {
        return rating;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public ManufacturerDO getManufacturer() {
        return manufacturer;
    }

    public static class CarDTOBuilder {
        private Long id;
        private Integer totalSeat;
        private String carType;
        private Float rating;
        private String licenseNo;
        private ManufacturerDO manufacturer;

        public CarDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public CarDTOBuilder setTotalSeat(Integer totalSeat) {
            this.totalSeat = totalSeat;
            return this;
        }

        public CarDTOBuilder setCarType(String carType) {
            this.carType = carType;
            return this;
        }

        public CarDTOBuilder setRating(Float rating) {
            this.rating = rating;
            return this;
        }

        public CarDTOBuilder setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
            return this;
        }

        public CarDTOBuilder setManufacturer(ManufacturerDO manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public CarDTO createCarDTO() {
            return new CarDTO(id, totalSeat, carType, rating, licenseNo, manufacturer);
        }

    }
}
