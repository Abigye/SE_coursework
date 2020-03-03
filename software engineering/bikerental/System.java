package uk.ac.ed.bikerental;

import java.util.Collection;
import java.util.HashSet;


public class System {	
	private static Collection<Provider> providers = new HashSet<>();
	
	public static Collection<Quote> generateQuotes(CustomerReq c){
		Collection<Quote> quotes = new HashSet<>();
		//iterate through the list of Providers to check which ones meet the customer requirement. 
		for(Provider p : providers) {
			boolean has = false;
			int hasNum = 0;
			for(BikeType reqb: c.reqBikes.keySet()) {
				//checking if the provider has enough available bikes of that bike type
					for(Bike b : p.bikes.keySet()) {
						if(b.getType().equals(reqb) && b.getAvailability(c.date)) {
							hasNum++;
							if(hasNum == c.reqBikes.get(reqb)) {
								has = true;
						}
					}
				}
			}
		
			//checking if the provider is near enough
			if(has && p.proLoc.isNearTo(c.locationOfHire)) {
				//getting the required number of bikes of each bike types in the requirement.
				Collection<Bike> bikes = new HashSet<>();
				for(BikeType reqb: c.reqBikes.keySet()) {
					int numOfBikes = c.reqBikes.get(reqb);
					while(numOfBikes!= 0) {
						for(Bike b : p.bikes.keySet()) {
							if(b.getType().equals(reqb) && b.getAvailability(c.date)) {
								bikes.add(b);
								numOfBikes--;
							}
						}
					}
					//If all the requirements the system generates a quote 
					//and adds it to the list of quotes to be presented to the customer
				Quote found = new Quote(bikes,p,p.totalRentalPrice(c.reqBikes,c.date.toDays()),p.totalDepositPrice(c.reqBikes));
				quotes.add(found);
				}
			}
		}
		return quotes;
	}
	
	public static Booking book(Customer cus,Quote quote,Provider returnTo,boolean delivery) {
		Booking booking = new Booking(quote,1,cus,returnTo, delivery);
		if((quote.provider.isPartner(returnTo))|| quote.provider.equals(returnTo)) {
			//updating the list of bookings for the provider
			quote.provider.addBookingDetails(booking);
			for(Bike b : quote.bikes) {
				//updating the number of the available bikes of the booked bike types for the provider
				b.setAvailability(cus.getReq().date);
			}
		}
		if(delivery) {
			DeliveryServiceFactory.getDeliveryService().scheduleDelivery(booking,
					quote.provider.proLoc,cus.loc,
					booking.customer.getReq().date.getStart());
			
		}
		return booking;
	}
	
	public static void recordreturnBikes(Collection<Bike> bikes,Booking booking) {
		Provider prov = null;
		for(Bike b : bikes) {
			prov = b.provider;
			break;
		}
		//bike is returned to partner, nothing happens if returned to the original provider 
		//cause availability is set when booking and we assumed the customer will always return bike by the return date.
		if(!booking.returnTo.equals(prov)) {
			for(Bike b : bikes) {
				b.setBikeWithParntner(true);
			}
			DeliveryServiceFactory.getDeliveryService().scheduleDelivery(booking,
					booking.returnTo.proLoc,prov.proLoc,
					booking.customer.getReq().date.getEnd());	
			
		}
	}
	
	
}