package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Bike{
	public final String bikeId;
	private BikeType type;
	private int age;
	private Collection<DateRange> unavailable = null;
	public final Provider provider;
	private boolean bikeWithPartner = false;
	private boolean bikeWithDriver = false;
	private BigDecimal depositAmount;// = type.getReplacementValue().multiply(provider.getDepositRate());
	public Bike(int age,String bikeId,BikeType type, Provider providedby) {
		this.age = age;
		this.bikeId = bikeId;
		this.type = type;
		this.provider = providedby;
		this.depositAmount = type.getReplacementValue().multiply(provider.depositRate);
	}
    public BikeType getType() {
    	return type;
    }
    public int getAge() {
    	return age;
    }
    public BigDecimal getdepositAmount() {
    	return depositAmount;
    }
    public void setAge(int bikeage) {
    	age = bikeage;
    }
    public boolean getBikeWithPartner() {
    	return bikeWithPartner;
    }
    public void setBikeWithParntner(boolean withpartner) {
    	bikeWithPartner = withpartner;
    }
    public boolean getBikeWithDriver() {
    	return bikeWithDriver;
    }
    public void setBikeWithDriver(boolean withdriver) {
    	bikeWithDriver = withdriver;
    }
    public void setAvailability(DateRange b) {
    	unavailable.add(b);
    }
    //checks if the dateRange given overlaps with any of the dates when the bike is unavailable.
    public boolean getAvailability(DateRange b) {
    	boolean avail = true;
    	for(DateRange d : unavailable) {
    		avail =  avail && !d.overlaps(b);
    	}
    	return avail;
    }
    
    
}