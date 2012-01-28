package no.komplett.solidify.specification;

import java.util.Date;

import no.komplett.solidify.data.Product;

public class ProductsExpiringSpecification extends AbstractSpecification {

	private Date expiresDate;
	
	private int counter = 0;

	public ProductsExpiringSpecification(Date expiresDate) {
		this.expiresDate = expiresDate;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof Product) {
			Product product = (Product) o;
			if(product.getExpiresDate() != null && product.getExpiresDate().before(expiresDate)){
				counter++;
				//System.out.println("Product with expiry date=[" + product.getExpiresDate() + "] [" + counter + "]");
			}
			
			return product.getExpiresDate() != null && product.getExpiresDate().after(expiresDate);
		} else {
			throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
		}
	}
}