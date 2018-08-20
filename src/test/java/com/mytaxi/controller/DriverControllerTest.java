package com.mytaxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytaxi.TestData;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.CarDriverDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.service.ICarDriverService;
import com.mytaxi.service.IDriverService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class DriverControllerTest {

    private static final String DRIVERS_ENDPOINT = "/v1/drivers/";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private IDriverService driverService;

    @Mock
    private ICarDriverService driverCarService;

    private MockMvc mockMvc;

    @InjectMocks
    private DriverController driverController;

    private JacksonTester<CarDTO> carDTOJacksonTester;
    private JacksonTester<List<DriverDTO>> carDTOListJacksonTester;
    private CarDriverDTO carDriverDTO;
    private CarDTO carDTO;
    private DriverDTO driverDTO;

    @Before
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        MockitoAnnotations.initMocks(DriverController.class);
        mockMvc = MockMvcBuilders.standaloneSetup(driverController).dispatchOptions(true).build();
        carDriverDTO = TestData.createCarDriverDTO();
        carDTO = TestData.createCarDTO();
        driverDTO = TestData.createDriverDTO();
    }

    @Test
    public void shouldDeleteDriver() throws Exception
    {
        doNothing().when(driverService).delete(any(Long.class));
        driverController.deleteDriver(driverDTO.getId());
        MvcResult result = mockMvc
                .perform(delete("/v1/drivers/{driverId}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void testFindByOnlineStatus() throws Exception
    {
        List<DriverDO> listOfDriverDO = TestData.createDriverDOList();
        doReturn(listOfDriverDO).when(driverService).find(any(OnlineStatus.class));
        driverController.findDriversByStatus(OnlineStatus.ONLINE);
        MvcResult result = mockMvc
                .perform(get("/v1/drivers")
                        .param("onlineStatus", OnlineStatus.ONLINE.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        Assert.assertTrue(responseBody.contains("TestOnlineDriver"));
    }

    @Test
    public void shouldCreateDriver() throws Exception
    {
        String jsonInString = mapper.writeValueAsString(driverDTO);
        DriverDO driverDO = TestData.createOnlineDriverDO();
        given(driverService.create(isA(DriverDO.class))).willReturn(driverDO);
        driverController.createDriver(driverDTO);
        MvcResult result = mockMvc
                .perform(post("/v1/drivers")
                        .contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonInString))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
        final String responseBody = result.getResponse().getContentAsString();
        System.out.println(responseBody);
        Assert.assertTrue(responseBody.contains("TestOnlineDriver"));

    }
}