package com.mytaxi.domainobject;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "manufacturer", uniqueConstraints = @UniqueConstraint(name = "name", columnNames = {"name"}))
public class ManufacturerDO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "date_created")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false, name = "name")
    @NotNull(message = "Car name cannot be null")
    private String name;

    @Column(nullable = false, name = "country")
    @NotNull(message = "Country of origin cannot be null")
    private String country;

    @Column(nullable = false)
    private Boolean deleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    private ManufacturerDO()
    {
    }

    public ManufacturerDO(String name, String country) {
        this.name = name;
        this.country = country;
        this.deleted = false;
    }
}