package uk.ac.ed.bikerental;

import java.util.HashMap;

public class CustomerReq {
	public HashMap<BikeType,Integer> reqBikes;
    public Location locationOfHire;
    public DateRange date;
    
    public CustomerReq (HashMap<BikeType,Integer> bikes, Location  hire, DateRange date){
    	this.reqBikes = bikes;
    	this.locationOfHire = hire;
    	this.date =date;
     }
}