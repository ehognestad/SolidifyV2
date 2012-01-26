package no.komplett.solidify.specification;

import java.util.Map;

import no.komplett.solidify.data.Product;

public class ProductFromCompanySpecification extends AbstractSpecification {

	Map<Integer, Double> companyMinPriceMap;

	private int counter = 0;

	public ProductFromCompanySpecification(Map<Integer, Double> companyMinPriceMap) {
		this.companyMinPriceMap = companyMinPriceMap;
	}

	public boolean isSatisfiedBy(Object o) {
		if (o instanceof Product) {
			Product product = (Product) o;

			if(companyMinPriceMap.get(product.getStoreId()) != null && product.getPrice() < companyMinPriceMap.get(product.getStoreId())){
				counter++;
				System.out.println("store id: " + product.getStoreId() + ", price: " + product.getPrice() + ", counter: " + counter);
			}
			return companyMinPriceMap.get(product.getStoreId()) != null && product.getPrice() < companyMinPriceMap.get(product.getStoreId());
		} else {
			throw new ClassCastException("Specification only for Product - received: " + o.getClass().getCanonicalName());
		}
	}
}