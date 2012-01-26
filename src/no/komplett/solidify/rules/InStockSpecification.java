package no.komplett.solidify.rules;

import no.komplett.solidify.data.Product;

public class InStockSpecification extends AbstractSpecification {
  
	private int quantityForSale;

  public InStockSpecification(int quantityForSale) {
    this.quantityForSale = quantityForSale;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof Product) {
    	Product car = (Product) o;
      return car.getQuantityForSale() > quantityForSale;
    } else {
      throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
    }
  }
}