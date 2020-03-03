package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;

public class Quote {
	public Collection<Bike> bikes;
    public final Provider provider;
    public BigDecimal totalPrice;
    public BigDecimal totalDeposit;
    
    public Quote(Collection<Bike> bikes, Provider provider,BigDecimal totalPrice,BigDecimal totalDeposit) {
    	this.bikes = bikes;
    	this.provider = provider;
    	this.totalPrice = totalPrice;
    	this.totalDeposit = totalDeposit;
    }
    
}
