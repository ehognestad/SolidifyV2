package no.komplett.solidify.rules;

import no.komplett.solidify.data.SalesData;

public class SaleInCustomerGroup extends AbstractSpecification {
  
	private int customerGroupId;

  public SaleInCustomerGroup(int customerGroupId) {
    this.customerGroupId = customerGroupId;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof SalesData) {
    	SalesData sale = (SalesData) o;
      return sale.getCustomerGroupId() == customerGroupId;
    } else {
      throw new ClassCastException("Specification only for Sale - received: " + o.getClass().getCanonicalName());
    }
  }
}