package com.mytaxi.domainvalue;

public enum SearchParameters {
	CAR_MANUFACTURER("car"),
	CAR_RATING("car"),
	CAR_LICENSENO("car"),
	CAR_CARTYPE("car"),
	CAR_TOTALSEAT("car"),
	DRIVER_ONLINESTATUS("driver"),
	DRIVER_USERNAME("driver");
	
	  private final String searchParameterCode;
      private SearchParameters(String  searchParameterCode) {
	        this.searchParameterCode = searchParameterCode;
	    }
      public String getSearchParameterCode() {
          return this.searchParameterCode;
      }
      
}
