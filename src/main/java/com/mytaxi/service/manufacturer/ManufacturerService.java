package com.mytaxi.service.manufacturer;

import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;


public interface ManufacturerService
{

    ManufacturerDO findManufacturerById(final Long manufacturerId) throws EntityNotFoundException;

    List<ManufacturerDO> findAllManufacturers();

    ManufacturerDO create(final ManufacturerDO manufacturer) throws EntityNotFoundException;

    void update(final ManufacturerDO manufacturer) throws EntityNotFoundException;

    void delete(final Long manufacturerId) throws EntityNotFoundException;


}
