package com.mytaxi.domainvalue;

public enum SearchParameters {
	car_manufacturer("car"),
	car_rating("car"),
	car_licenseno("car"),
	car_cartype("car"),
	car_totalseat("car"),
	driver_onlinestaus("driver"),
	driver_username("driver");
	
	  private final String searchParameterCode;
      private SearchParameters(String  searchParameterCode) {
	        this.searchParameterCode = searchParameterCode;
	    }
      public String getSearchParameterCode() {
          return this.searchParameterCode;
      }
      
}
