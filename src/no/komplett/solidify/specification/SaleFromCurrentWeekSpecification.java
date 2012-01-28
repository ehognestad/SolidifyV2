package no.komplett.solidify.specification;

import java.util.Calendar;
import java.util.Date;

import no.komplett.solidify.data.SalesData;

public class SaleFromCurrentWeekSpecification extends AbstractSpecification {
  
	private Date today;

  public SaleFromCurrentWeekSpecification(Date today) {
    this.today = today;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof SalesData) {
    	SalesData sale = (SalesData) o;
    	Calendar salesCalendar = Calendar.getInstance();
    	salesCalendar.set(Calendar.YEAR, sale.getYear());
    	salesCalendar.set(Calendar.MONTH, sale.getMonth());
    	salesCalendar.set(Calendar.DATE, sale.getDay());
    	
    	Calendar todaysCalendar = Calendar.getInstance();
    	todaysCalendar.setTime(today);
    	
    	int currentSaleWeek = salesCalendar.get(Calendar.WEEK_OF_YEAR);
//    	System.out.println("currentSaleWeek: " + currentSaleWeek);
    	
      return currentSaleWeek == todaysCalendar.get(Calendar.WEEK_OF_YEAR);
    } else {
      throw new ClassCastException("Specification only for Sale - received: " + o.getClass().getCanonicalName());
    }
  }
}