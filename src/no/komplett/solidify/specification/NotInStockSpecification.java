package no.komplett.solidify.specification;

import no.komplett.solidify.data.Product;

public class NotInStockSpecification extends AbstractSpecification {

	private int quantityForSale;
	
	private int counter = 0;

	public NotInStockSpecification(int quantityForSale) {
		this.quantityForSale = quantityForSale;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof Product) {
			Product product = (Product) o;
			
			if(product.getQuantityForSale() < quantityForSale){
				counter++;
				//System.out.println("Product with quantity for sale=[" + product.getQuantityForSale() + "] [" + counter + "]");
			}
			
			return product.getQuantityForSale() < quantityForSale;
		} else {
			throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
		}
	}
}