package no.komplett.solidify.specification;

import no.komplett.solidify.data.SalesData;

public class SaleFromStoreSpecification extends AbstractSpecification {
  
	private int storeId;

  public SaleFromStoreSpecification(int storeId) {
    this.storeId = storeId;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof SalesData) {
    	SalesData sale = (SalesData) o;
      return storeId == sale.getStoreId();
    } else {
      throw new ClassCastException("Specification only for Sale - received: " + o.getClass().getCanonicalName());
    }
  }
}