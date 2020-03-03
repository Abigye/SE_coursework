package uk.ac.ed.bikerental;
import java.util.Collection;
import java.util.Objects;

public class Customer {
	public final String name;
	public final int phoneNo;
    public final Location loc;
    private CustomerReq req;
    
    public Customer(String name ,int phoneNo, Location location, CustomerReq req) {
    	this.name = name;
    	this.phoneNo = phoneNo;
    	this.req = req;
        this.loc = location;
    }
    public CustomerReq getReq(){
        return this.req;   
    }
    public void setReq(CustomerReq need) {
        Objects.requireNonNull(need.date); 
        Objects.requireNonNull(need.reqBikes); 
        Objects.requireNonNull(need.locationOfHire);
        this.req = need;
    }
 
    public Collection<Quote> findQuotes(){
    	return System.generateQuotes(req);
    }
    public Booking book(Quote quote, Provider returnTo,boolean delivery) {
    	return System.book(this,quote,returnTo,delivery);
    }
    
    
}
