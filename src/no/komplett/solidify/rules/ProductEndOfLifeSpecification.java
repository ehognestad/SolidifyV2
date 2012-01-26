package no.komplett.solidify.rules;

import no.komplett.solidify.data.Product;

public class ProductEndOfLifeSpecification extends AbstractSpecification {
  
	private String vmstaCode;

  public ProductEndOfLifeSpecification(String vmstaCode) {
    this.vmstaCode = vmstaCode;
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