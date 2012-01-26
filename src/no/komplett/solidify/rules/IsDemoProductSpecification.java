package no.komplett.solidify.rules;

import java.util.Set;

import no.komplett.solidify.data.Product;

public class IsDemoProductSpecification extends AbstractSpecification {
  
	private Set<Integer> demoProductTypeIds;

  public IsDemoProductSpecification(Set<Integer> demoProductTypeIds) {
    this.demoProductTypeIds = demoProductTypeIds;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof Product) {
    	Product product = (Product) o;
      return demoProductTypeIds.contains(product.getSpart());
    } else {
      throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
    }
  }
}