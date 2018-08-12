package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManufacturerDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "Car name cannot be null")
    private String name;

    @NotNull(message = "Country of origin cannot be null")
    private String country;
    private ManufacturerDTO() {
    }

    public ManufacturerDTO(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public static ManufacturerDTOBuilder newBuilder() {
        return new ManufacturerDTOBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public static class ManufacturerDTOBuilder {
        private Long id;
        private String name;
        private String country;

        public ManufacturerDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ManufacturerDTOBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ManufacturerDTOBuilder setCountry(String country) {
            this.country = country;
            return this;
        }

        public ManufacturerDTO createManufacturerDTO() {
            return new ManufacturerDTO(id, name, country);
        }
    }
}
