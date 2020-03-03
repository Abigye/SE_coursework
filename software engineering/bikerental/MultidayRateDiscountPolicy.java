package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;


public class MultidayRateDiscountPolicy implements PricingPolicy  {
    
    public final Provider provider;
    public MultidayRateDiscountPolicy(Provider provider) {
    	this.provider = provider;
    }
	public void setDailyRentalPrice(BikeType bikeType, BigDecimal dailyPrice) { 
    	provider.bikeAndRentalValue.replace(bikeType,dailyPrice);
    }
    public BigDecimal calculatePrice(Collection<Bike> bikes, DateRange duration) { 
    BigDecimal discountrate;
    BigDecimal totalPrice = BigDecimal.valueOf(0);
    
    if (duration.toDays() >= 1 && duration.toDays() <= 2) {
        discountrate = new BigDecimal(0.00);
        }
    else if(duration.toDays() >= 3 && duration.toDays() <= 6) { 
         discountrate = new BigDecimal(0.05);
        }
    else if(duration.toDays() >= 7 && duration.toDays() <= 13) {
    	discountrate = new BigDecimal(0.10);
        }
    else {
    	discountrate = new BigDecimal(0.15);
    }
    for (Bike bike : bikes) {     
        totalPrice = totalPrice.add(bike.provider.getRentalPrice(bike.getType()).multiply((discountrate).multiply(BigDecimal.valueOf(duration.toDays()))));
       }
       return totalPrice; 
    }
    

}
