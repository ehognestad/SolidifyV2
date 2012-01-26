package no.komplett.solidify.specification;

import no.komplett.solidify.data.SalesData;

public class SaleInCustomerGroupSpecification extends AbstractSpecification {
  
	private int customerGroupId;

  public SaleInCustomerGroupSpecification(int customerGroupId) {
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