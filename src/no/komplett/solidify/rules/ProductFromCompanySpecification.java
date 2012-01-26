package no.komplett.solidify.rules;

import no.komplett.solidify.data.Product;

public class ProductFromCompanySpecification extends AbstractSpecification {
  
	private int storeId;

  public ProductFromCompanySpecification(int storeId) {
    this.storeId = storeId;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof Product) {
    	Product product = (Product) o;
      return product.getStoreId() == storeId;
    } else {
      throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
    }
  }
}