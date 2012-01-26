package no.komplett.solidify.rules;

import no.komplett.solidify.data.Product;

public class ProductPriceLessThanSpecification extends AbstractSpecification {
  
	private double price;

  public ProductPriceLessThanSpecification(double price) {
    this.price = price;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof Product) {
    	Product product = (Product) o;
      return product.getPrice() < price;
    } else {
      throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
    }
  }
}