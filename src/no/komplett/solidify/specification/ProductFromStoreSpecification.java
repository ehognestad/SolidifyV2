package no.komplett.solidify.specification;

import no.komplett.solidify.data.Product;

public class ProductFromStoreSpecification extends AbstractSpecification {

	private int storeId;

	public ProductFromStoreSpecification(int storeId) {
		this.storeId = storeId;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof Product) {
			Product product = (Product) o;
			//System.out.println("product storeid: " + product.getStoreId() + ", storeid: " + storeId);
			return product.getStoreId() == storeId;
		} else {
			throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
		}
	}
}