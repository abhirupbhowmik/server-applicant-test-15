package com.mytaxi.domainvalue;

public enum SearchParameters {
	car_manufacturer("car"),
	car_rating("car"),
	car_licenseNo("car"),
	car_carType("car"),
	car_totalSeat("car"),
	driver_onlineStatus("driver"),
	driver_username("driver");
	
	  private final String searchParameterCode;
      private SearchParameters(String  searchParameterCode) {
	        this.searchParameterCode = searchParameterCode;
	    }
      public String getSearchParameterCode() {
          return this.searchParameterCode;
      }
      
}
