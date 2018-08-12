package com.mytaxi.service.manufacturer;

import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultManufacturerService implements ManufacturerService
{

    @Autowired
    private ManufacturerRepository manufacturerRepository;


    @Override
    public ManufacturerDO findManufacturerById(final Long manufacturerId) throws EntityNotFoundException
    {
        return checkManufacturer(manufacturerId);
    }

    @Override
    public List<ManufacturerDO> findAllManufacturers()
    {
        return (List<ManufacturerDO>) manufacturerRepository.findAll();
    }


    @Override
    public ManufacturerDO create(final ManufacturerDO manufacturer) throws EntityNotFoundException
    {
        return manufacturerRepository.save(manufacturer);
    }


    @Override
    @Transactional
    public void update(final ManufacturerDO manufacturer) throws EntityNotFoundException
    {
        ManufacturerDO updateManufacturer = checkManufacturer(manufacturer.getId());
        updateManufacturer.setCountry(manufacturer.getCountry());
    }

    @Override
    @Transactional
    public void delete(final Long manufacturerId) throws EntityNotFoundException
    {
        ManufacturerDO manufacturer = checkManufacturer(manufacturerId);
        manufacturer.setDeleted(Boolean.TRUE);
    }

    private ManufacturerDO checkManufacturer(final Long manufacturerId) throws EntityNotFoundException
    {
        return manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new EntityNotFoundException("Not able to find manufacturer with id = " + manufacturerId));
    }
}
