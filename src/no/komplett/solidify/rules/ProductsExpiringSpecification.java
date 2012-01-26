package no.komplett.solidify.rules;

import java.util.Date;

import no.komplett.solidify.data.Product;

public class ProductsExpiringSpecification extends AbstractSpecification {
  
	private Date expiresDate;

  public ProductsExpiringSpecification(Date expiresDate) {
    this.expiresDate = expiresDate;
  }

  public boolean isSatisfiedBy(Object o) {
    if (o instanceof Product) {
    	Product car = (Product) o;
      return car.getExpiresDate().after(expiresDate);
    } else {
      throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
    }
  }
}