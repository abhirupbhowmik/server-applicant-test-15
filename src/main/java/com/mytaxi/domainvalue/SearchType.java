package com.mytaxi.domainvalue;

public enum SearchType {
	CAR("car"),DRIVER("driver");
	
	  private final String searchTypeCode;
      private SearchType(String  searchTypeCode) {
	        this.searchTypeCode = searchTypeCode;
	    }
      public String getSearchTypCode() {
          return this.searchTypeCode;
      }
      
}
