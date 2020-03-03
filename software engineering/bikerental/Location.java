package uk.ac.ed.bikerental;

/**
 * Represents the location of the customer or the provider.
 * 
 * @author s1891419
 *
 */
public class Location {
	/**
	 * postcode of the customer or provider
	 */
    private String postcode;
    /**
    * street address of the customer or provider
    */
    private String address;
    
    public Location(String postcode, String address) {
        assert postcode.length() >= 6;
        this.postcode = postcode;
        this.address = address;
    }
    /**
     * 
     * @param postcodeofOther is another location
     * @return true if the other location is in the same city
     */
    public boolean isNearTo(Location postcodeofOther) {
    	String a = postcodeofOther.postcode.substring(0, 2);
    	String b = postcode.substring(0, 2);
    	if(a.equals(b)) {
    		return true;
    	}
         	return false;
    }
    /**
     * 
     * @return the postcode 
     */
    public String getPostcode() {
        return postcode;
    }
    /**
     * 
     * @return the street address
     */
    public String getAddress() {
        return address;
    }
    
    // You can add your own methods here
}
