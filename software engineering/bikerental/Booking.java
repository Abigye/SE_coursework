package uk.ac.ed.bikerental;


public class Booking implements Deliverable {
	
	public final Quote quote;
	public final int orderNo;
	public final Customer customer;
	public final Provider returnTo;
	public final boolean delivery;
	public boolean successful = false; // set to true when collected or driver delivers it.



	public Booking(Quote quote,int orderNo,Customer cus, Provider delivaredTo, boolean delivery) {
		this.quote = quote;
		this.orderNo = orderNo;
		this.customer = cus;
		this.returnTo = delivaredTo;
		this.delivery = delivery;
	}
	
	public void onPickup() {
		for(Bike b:quote.bikes) {
			b.setBikeWithDriver(true);
		}
    }
	//update the bike status and if it dropping it off to the customer updates the booking to successful is true.
    public void onDropoff() {
    	boolean withPartner = false;
		for(Bike b:quote.bikes) {
			withPartner = b.getBikeWithPartner();
			b.setBikeWithDriver(false);
		}
    	if(!withPartner) {
    		successful = true;
    	}else {
    		for(Bike b:quote.bikes) {
    			b.setBikeWithParntner(false);
    		}
    	}
    }
    
	
	

}
