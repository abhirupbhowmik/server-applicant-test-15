package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.EngineType;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
    private Long id;

    @NotNull(message = "Seat numbers can not be null!")
    private String totalSeat;

    private EngineType carType;

    private String rating;

    @NotNull(message = "Car numbers can not be null!")
    private String licenseNo;

    private String manufacturer;
    
    
	public CarDTO() {
    }

    public CarDTO(Long id, String totalSeat, EngineType carType, String rating, String licenseNo, String manufacturer) {
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

    public String getTotalSeat() {
        return totalSeat;
    }

    public EngineType getCarType() { return carType; }

    public String getRating() {
        return rating;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public static class CarDTOBuilder {
        private Long id;
        private String totalSeat;
        private EngineType carType;
        private String rating;
        private String licenseNo;
        private String manufacturer;
        //private DriverDTO driverDetails;

        public CarDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public CarDTOBuilder setTotalSeat(String totalSeat) {
            this.totalSeat = totalSeat;
            return this;
        }

        public CarDTOBuilder setCarType(EngineType carType) {
            this.carType = carType;
            return this;
        }

        public CarDTOBuilder setRating(String rating) {
            this.rating = rating;
            return this;
        }

        public CarDTOBuilder setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
            return this;
        }

        public CarDTOBuilder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }
        
        /*public CarDTOBuilder setDriverDetails(DriverDTO driverDetails) {
            this.driverDetails = driverDetails;
            return this;
        }*/
        
        

        public CarDTO createCarDTO() {
            return new CarDTO(id, totalSeat, carType, rating, licenseNo, manufacturer);
        }

    }
}
