package no.komplett.solidify.specification;

import java.util.Set;

import no.komplett.solidify.data.Product;

public class IsDemoProductSpecification extends AbstractSpecification {

	private Set<Integer> demoProductTypeIds;

	private static int counter = 0;

	public IsDemoProductSpecification(Set<Integer> demoProductTypeIds) {
		this.demoProductTypeIds = demoProductTypeIds;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof Product) {
			Product product = (Product) o;
			
			if(demoProductTypeIds.contains(product.getSpart())){
				counter++;
				System.out.println("Demo product with spart=[" + product.getSpart() + "] [" + counter + "]");
			}
			
			return demoProductTypeIds.contains(product.getSpart());
		} else {
			throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
		}
	}
}