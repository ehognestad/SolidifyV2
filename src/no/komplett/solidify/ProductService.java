package no.komplett.solidify;

import java.util.Collection;

import no.komplett.solidify.data.Product;

public interface ProductService {

	
	public Collection<Product> getProductsInStockNotExpiringWithinWeek();
}
