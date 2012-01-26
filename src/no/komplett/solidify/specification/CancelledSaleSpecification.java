package no.komplett.solidify.specification;

import no.komplett.solidify.data.SalesData;

public class CancelledSaleSpecification extends AbstractSpecification {
  
	private String itemStatus;

  public CancelledSaleSpecification(String itemStatus) {
    this.itemStatus = itemStatus;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof SalesData) {
    	SalesData sale = (SalesData) o;
      return sale.getItemStatus().equalsIgnoreCase(itemStatus);
    } else {
      throw new ClassCastException("Specification only for Sale - received: " + o.getClass().getCanonicalName());
    }
  }
}