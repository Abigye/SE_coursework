package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import static org.junit.Assert.*;

public class BikeType {

	public final String typeOfbike;
	private BigDecimal replacementValue;
	

	public BikeType(String typeOfBike,BigDecimal replacementValue) {
		this.typeOfbike = typeOfBike;
		this.replacementValue = replacementValue;
	}
    public BigDecimal getReplacementValue() {
    	return replacementValue;
    }

    public void setReplacementValue(BigDecimal replacement) {
    	assertFalse("The replacememt can't be negative", replacement.compareTo(BigDecimal.valueOf(0))<0);
    	replacementValue= replacement;
    }
 
}