package no.komplett.solidify.specification;

import java.util.Calendar;
import java.util.Date;

import no.komplett.solidify.data.SalesData;

public class SaleFromCurrentMonthSpecification extends AbstractSpecification {

	private Date today;

	public SaleFromCurrentMonthSpecification(Date today) {
		this.today = today;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof SalesData) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(today);

			SalesData sale = (SalesData) o;
//			System.out.println("Sales month: " + sale.getMonth() + ", current year: " + calendar.get(Calendar.YEAR) + ", urrent month: "  + calendar.get(Calendar.MONTH));

			return sale.getYear() == calendar.get(Calendar.YEAR) && sale.getMonth() == calendar.get(Calendar.MONTH) ;
		} else {
			throw new ClassCastException("Specification only for Sale - received: " + o.getClass().getCanonicalName());
		}
	}
}