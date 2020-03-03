/**
 * Represents the start and end date of when a bike is not available.
 * It Uses LocalDate to represent the dates
 * 
 * Please see the{@link docs.oracle.com/javase/8/docs/api/java/time/LocalDate} to see how LocalDate works
 * 
 * @auther s1891419
 * 
 */
package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DateRange {
	/**
	 * start date represents the collection date of the bike by the customer
	 */
    private LocalDate start;
    
    /**
    * end date represents the return date of the bike to a provider.
    */
    
    private LocalDate end;
    
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    /**
     * 
     * @return collection date of the bike by the customer
     */
    public LocalDate getStart() {
        return this.start;
    }
   /**
    * 
    * @return return date of the bike to a provider.
    */
    public LocalDate getEnd() {
        return this.end;
    }
    /**
     * 
     * @return number of years between the startDate and endDate
     */
    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }
    /**
     * 
     * @return number of days between the startDate and the endDate
     */
    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }
    /**
     * 
     * @param other another booking's DateRange
     * @return true if the bookings overlaps 
     */
    public Boolean overlaps(DateRange other) {
            if((end.isAfter(other.start)&&(end.isBefore(other.end)))||
            		((start.isBefore(other.end))&& (start.isAfter(other.start)))){
                return true;
            }
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(end, start);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
    
    // You can add your own methods here
    
}
