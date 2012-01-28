package no.komplett.solidify.specification;

import no.komplett.solidify.data.SalesData;
import no.komplett.solidify.util.ItemSalesStatus;

public class SaleStatusSpecification extends AbstractSpecification {

	private ItemSalesStatus itemStatus;

	public SaleStatusSpecification(ItemSalesStatus itemStatus) {
		this.itemStatus = itemStatus;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof SalesData) {
			SalesData sale = (SalesData) o;
			return sale.getItemStatus().equalsIgnoreCase(itemStatus.getConstant());
		} else {
			throw new ClassCastException("Specification only for Sale - received: " + o.getClass().getCanonicalName());
		}
	}
}