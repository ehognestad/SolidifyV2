package no.komplett.solidify.specification;

import java.util.Collection;

import no.komplett.solidify.data.Product;
import no.komplett.solidify.data.SalesData;

public class SalesToRemoveSpecification extends AbstractSpecification {

	Collection<Integer> productIds;

	public SalesToRemoveSpecification(Collection<Integer> productsIds) {
		this.productIds = productsIds;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof SalesData) {
			SalesData sale = (SalesData) o;

			return productIds.contains(sale.getProductId());
		} else {
			throw new ClassCastException("Specification only for SalesData - received: " + o.getClass().getCanonicalName());
		}
	}
}