package no.komplett.solidify.specification;

import no.komplett.solidify.data.Product;

public class ProductEndOfLifeSpecification extends AbstractSpecification {

	//End of life code
	private String vmstaCode = "03";

	public ProductEndOfLifeSpecification() {
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof Product) {
			Product product = (Product) o;
			return product.getVmsta().equalsIgnoreCase(vmstaCode);
		} else {
			throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
		}
	}
}