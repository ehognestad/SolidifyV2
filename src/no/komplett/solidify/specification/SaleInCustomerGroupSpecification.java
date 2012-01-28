package no.komplett.solidify.specification;

import java.util.Set;

import no.komplett.solidify.data.SalesData;

public class SaleInCustomerGroupSpecification extends AbstractSpecification {

	private Set<Integer> customerGroupIds;

	public SaleInCustomerGroupSpecification(Set<Integer> customerGroupIds) {
		this.customerGroupIds = customerGroupIds;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof SalesData) {
			SalesData sale = (SalesData) o;
			return customerGroupIds.contains(sale.getCustomerGroupId());
		} else {
			throw new ClassCastException("Specification only for Sale - received: " + o.getClass().getCanonicalName());
		}
	}
}