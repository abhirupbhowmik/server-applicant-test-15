package com.mytaxi.controller.mapper;

import com.mytaxi.TestData;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class DriverMapperTest
{
    @Mock
    private DriverMapper driverMapper;

    @BeforeClass
    public static void setUp()
    {
        MockitoAnnotations.initMocks(DriverMapper.class);
    }

    @Test
    public void driverDOShouldMapDriverDTO(){

        DriverDO driverDO = TestData.createOnlineDriverDO();
        DriverDTO driverDTO = driverMapper.makeDriverDTO(driverDO);
        assertThat(driverDTO).isNotNull();
        assertThat(driverDTO.getId()).isEqualTo(driverDO.getId());
        assertThat(driverDTO.getUsername()).isEqualTo(driverDO.getUsername());
        assertThat(driverDTO.getPassword()).isEqualTo(driverDO.getPassword());
        assertThat(driverDTO.getStatus()).isEqualTo(driverDO.getOnlineStatus());
        assertThat(driverDTO.getCoordinate()).isEqualTo(driverDO.getCoordinate());

    }

    @Test
    public void driverDTOShouldMapDriverDO(){

        DriverDTO driverDTO = TestData.createDriverDTO();
        DriverDO driverDO = driverMapper.makeDriverDO(driverDTO);
        assertThat(driverDO).isNotNull();
        assertThat(driverDO.getId()).isEqualTo(driverDTO.getId());
        assertThat(driverDO.getUsername()).isEqualTo(driverDTO.getUsername());
        assertThat(driverDO.getPassword()).isEqualTo(driverDTO.getPassword());
        assertThat(driverDO.getOnlineStatus()).isEqualTo(driverDTO.getStatus());
    }

    @Test
    public void driverDTOListShouldMapDriverDOList(){


        List<DriverDO> driverDOList = TestData.createDriverDOList();
        List<DriverDTO> driverDTOList = driverMapper.makeDriverDTOList(driverDOList);
        assertThat(driverDOList).isNotNull();
        assertThat(driverDTOList.contains(driverDOList));

    }
}
