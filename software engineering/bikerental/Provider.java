package uk.ac.ed.bikerental;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Collection;
import java.util.HashMap;


import java.math.BigDecimal;

public class Provider {
	public final String fullName;
	public final Location proLoc;
	public final LocalTime openingTime;
	public final LocalTime closingTime;
	public final int phoneNo;
	public final BigDecimal depositRate;
	private Collection<Provider> partners;
	public HashMap<BikeType,BigDecimal> bikeAndRentalValue;
	private Collection<Booking> listOfBookingsDetails = null;
	public HashMap<Bike,Collection<DateRange>> bikes; //bikes with their unavailable dates.
	
	public Provider(String name,Location proLoc,LocalTime openingTime,LocalTime closingTime,int phoneNo,
			BigDecimal depositRate,Collection<Provider> partners,HashMap<BikeType,BigDecimal> bikeAndRentalValue,HashMap<Bike,Collection<DateRange>> bikes) {
		this.fullName = name;
		this.proLoc = proLoc;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.phoneNo = phoneNo;
		this.depositRate = depositRate;
		this.partners = partners;
		this.bikeAndRentalValue = bikeAndRentalValue;
		this.bikes = bikes;
	}
	
	public Collection<Provider> getPartners(){
		return partners;
	}
	public BigDecimal getRentalPrice(BikeType type) {
		return bikeAndRentalValue.get(type);
	}
	
	public void setRentalPrice(BikeType bikeType, BigDecimal dailyPrice) {
		MultidayRateDiscountPolicy a = new MultidayRateDiscountPolicy(this);
		a.setDailyRentalPrice(bikeType,dailyPrice);
	}
	
	public void addPartner(Provider partner) {
		partners.add(partner);
	}
	public void removePartner(Provider partner) {
		partners.remove(partner);
	}
	public boolean isPartner(Provider partner) {
		return partners.contains(partner);	
	}
	//calculating total rental price without discount- number of bikes for each bike type* rentalPrice * number of days
	public BigDecimal totalRentalPrice(HashMap<BikeType,Integer> bikes, long duration) {
		BigDecimal totalValue = BigDecimal.valueOf(0);
		for(BikeType bike: bikes.keySet()) {
			totalValue = totalValue.add(getRentalPrice(bike).multiply
					(new BigDecimal(bikes.get(bike))).multiply(BigDecimal.valueOf(duration)));
		}
		return totalValue;
	}
	//get bikes and calls the MultidayRateDiscountPolicy
	public BigDecimal calculatePriceWithDiscount(HashMap<BikeType,Integer> bikes, DateRange duration) {
		Collection<Bike> bike = new HashSet<>();
		for(BikeType b: bikes.keySet()) {
			int x = 0;
			for(Bike bi : this.bikes.keySet()) {
				//dont care if bike is unavailable because they all have the same rental value.
					if(bi.getType().equals(b)) {
						bike.add(bi);
						x++;
					}
					if(x==bikes.get(b)) {
						break;
				}
			}
		}
		MultidayRateDiscountPolicy a = new MultidayRateDiscountPolicy(this);
		return a.calculatePrice(bike,duration);
		
	}
	//replacementValue * depositrate * number of bikes
	public BigDecimal totalDepositPrice(HashMap<BikeType,Integer> bikes) {
		BigDecimal totalValue = BigDecimal.valueOf(0);
		for(BikeType biketype: bikes.keySet()) {
			totalValue = totalValue.add(biketype.getReplacementValue().multiply
					(depositRate).multiply(new BigDecimal(bikes.get(biketype))));
		}
		return totalValue;
	}
	public void addBookingDetails(Booking b) {
		listOfBookingsDetails.add(b);
	}
	public void RecordreturnBikes(Collection<Bike> bikes,Booking booking) {
   	 System.recordreturnBikes(bikes,booking);
   }

	

	
	
	

}
